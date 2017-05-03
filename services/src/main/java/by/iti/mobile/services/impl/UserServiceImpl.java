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
        return null;
    }

    public UserDto getById(Long id) throws ServiceException {
        return null;
    }

    public void update(UserDto userDTO) throws ServiceException {

    }

    public void deleteById(Long id) throws ServiceException {

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
        return null;
    }

    public UserDto getByUsernamePassword(String username, String password) throws ServiceException {
        return null;
    }

    public Long getCount() throws ServiceException {
        return null;
    }

    public List<UserDto> getByGap(int offset, int quantity) throws ServiceException {
        return null;
    }
}
