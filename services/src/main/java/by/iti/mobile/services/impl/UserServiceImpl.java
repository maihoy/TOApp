package by.iti.mobile.services.impl;

import by.iti.mobile.constants.ServiceConstants;
import by.iti.mobile.dao.UserDao;
import by.iti.mobile.dao.UserDataDao;
import by.iti.mobile.dao.UserTariffDao;
import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.pojo.User;
import by.iti.mobile.pojo.UserData;
import by.iti.mobile.pojo.UserTariff;
import by.iti.mobile.services.UserService;
import by.iti.mobile.utils.UserConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserDataDao userDataDao;
    @Autowired
    UserTariffDao tariffDao;

    @Autowired
    private UserConverter userConverter;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public Long insert(UserDto userDTO) throws ServiceException {
        User user = null;
        UserData userData = null;
        Long userId;
        Set<UserTariff> userTariffs = null;
        try {
            if ((userDTO.getUserId() != null) && (userDTO.getUserDataId() != null)) {
                userData = userDataDao.getById(userDTO.getUserDataId());
                user = userDao.getById(userDTO.getUserId());
            }
            if ((userData == null) && (user == null)) {
                userData = new UserData();
                user = new User();
            }
            userId = userDao.insert(userConverter.toUserPOJO(userDTO, user));
            userData = userConverter.toUserDataPOJO(userDTO, userData);
            userData.setUser(userDao.getById(userId));
            userDataDao.insert(userData);

            List<Long> idsToRemove ;
            idsToRemove = tariffDao.getAllIds();

            for (UserTariff userTariff : userDTO.getUserTariffs()) {
                Long id =tariffDao.insert(userConverter.toUserTariffPOJO(userId,userTariff));
                idsToRemove.remove(id);
                logger.info(idsToRemove);
            }
            for (Long l:idsToRemove) {
                tariffDao.deleteById(l);
            }
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return userId;
    }

    public UserDto getById(Long userDataId) throws ServiceException {
        UserData userData;
        try {
            userData = userDataDao.getById(userDataId);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException();
        }
        return userConverter.toUserDTO(userData);
    }

    public void update(UserDto userDTO) throws ServiceException {
        User userPOJO = null;
        UserData userData = null;
        try {
            if ((userDTO.getUserId() != null) && (userDTO.getUserDataId() != null))
                userPOJO = userDao.getById(userDTO.getUserId());
            userData = userDataDao.getById(userDTO.getUserDataId());
            if (userPOJO == null) {
                throw new ServiceException();
            }
            userPOJO = userConverter.toUserPOJO(userDTO, userPOJO);
            userDao.update(userPOJO);
            userData = userConverter.toUserDataPOJO(userDTO, userData);
            userDataDao.update(userData);


        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    public void deleteById(Long userDataId) throws ServiceException {
        try {
            Long userId = userDataDao.getById(userDataId).getUser().getId();
            userDataDao.deleteById(userDataId);
            userDao.deleteById(userId);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    public List<UserDto> getAll() throws ServiceException {
        List<UserData> userDatas;
        List<UserDto> userDTOs;
        try {
            userDatas = userDataDao.getAll();
            logger.info(userDatas);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        userDTOs = new ArrayList<>(userDatas.size());
        for (UserData userData : userDatas) {
            userDTOs.add(userConverter.toUserDTO(userData));
        }
        return userDTOs;
    }


    public Long getCount() throws ServiceException {
        Long count;
        try {
            count = userDataDao.getCount();
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return count;
    }

    public List<UserDto> getByGap(int offset, int quantity) throws ServiceException {
        List<UserData> userPOJOs;
        List<UserDto> userDTOs = new LinkedList<>();
        try {
            userPOJOs = userDataDao.getByGap(offset, quantity);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (UserData userData : userPOJOs) {
            userDTOs.add(userConverter.toUserDTO(userData));
        }
        return userDTOs;
    }
}
