package by.iti.mobile.services.impl;

import by.iti.mobile.dao.UserDao;
import by.iti.mobile.constants.ServiceConstants;
import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.pojo.User;
import by.iti.mobile.services.UserService;
import by.iti.mobile.utils.UserConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public Long insert(UserDto userDTO) throws ServiceException {
        User userPOJO = null;
        Long id;
        logger.error(userDTO.toString());
        try {
            if (userDTO.getId() != null) {
                userPOJO = userDao.getById(userDTO.getId());
            }
            if (userPOJO == null)
                userPOJO = new User();
            id = userDao.insert(userConverter.toUserPOJO(userDTO, userPOJO));
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return id;
    }

    public UserDto getById(Long id) throws ServiceException {
        User userPOJO;
        try {
            userPOJO = userDao.getById(id);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException();
        }
        return userConverter.toUserDTO(userPOJO);
    }

    public void update(UserDto userDTO) throws ServiceException {
        User userPOJO = null;
        try {
            if (userDTO.getId() != null)
                userPOJO = userDao.getById(userDTO.getId());
            if (userPOJO == null) {
                throw new ServiceException();
            }
            userPOJO = userConverter.toUserPOJO(userDTO, userPOJO);
            userDao.update(userPOJO);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        try {
            userDao.deleteById(id);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
    }

    public List<UserDto> getAll() throws ServiceException {
        List<User> userPOJOs;
        List<UserDto> userDTOs;
        try {
            userPOJOs = userDao.getAll();
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        userDTOs = new ArrayList<>(userPOJOs.size());
        for (User user: userPOJOs) {
            userDTOs.add(userConverter.toUserDTO(user));
        }
        return userDTOs;
    }

    public UserDto getByUsername(String username) throws ServiceException {
        User userPOJO;
        try {
           userPOJO= userDao.getByUsername(username);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return userConverter.toUserDTO(userPOJO);
    }

    public UserDto getByUsernamePassword(String username, String password) throws ServiceException {
        User userPOJO;
        try {
            userPOJO= userDao.getByUsernamePassword(username,password);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e.getCause());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new ServiceException(e);
        }
        return userConverter.toUserDTO(userPOJO);
    }

    public Long getCount() throws ServiceException {
        Long count;
        try {
            count = userDao.getCount();
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        return count;
    }

    public List<UserDto> getByGap(int offset, int quantity) throws ServiceException {
        List<User> userPOJOs;
        List<UserDto> userDTOs = new LinkedList<>();
        try {
            userPOJOs = userDao.getByGap(offset, quantity);
        } catch (DaoExceptions e) {
            logger.error(ServiceConstants.ERROR_SERVICE, e);
            throw new ServiceException(e);
        }
        for (User userPOJO : userPOJOs) {
            userDTOs.add(userConverter.toUserDTO(userPOJO));
        }
        return userDTOs;
    }
}
