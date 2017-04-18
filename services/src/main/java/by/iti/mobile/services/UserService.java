package by.iti.mobile.services;

import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.ServiceException;

import java.util.List;

/**
 * Created by j on 17.4.17.
 */
public interface UserService {
    Long insert(UserDto userDTO) throws ServiceException;

    UserDto getById(Long id) throws ServiceException;

    void update(UserDto userDTO) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    List<UserDto> getAll() throws ServiceException;

    UserDto getByUsername(String username) throws ServiceException;

    UserDto getByUsernamePassword(String username, String password) throws ServiceException;

    Long getCount() throws ServiceException;

    List<UserDto> getByGap(int offset, int quantity) throws ServiceException;

}
