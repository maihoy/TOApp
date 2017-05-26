package by.iti.mobile.utils;

import by.iti.mobile.dao.StreetDao;
import by.iti.mobile.dao.UserDao;
import by.iti.mobile.dao.UserTariffDao;
import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {

    @Autowired
    UserDao userDao;
    @Autowired
    StreetDao streetDao;
    @Autowired
    UserTariffDao userTariffDao;

    public UserDto toUserDTO(UserData userData) {

        UserDto userDTO = null;
        if (userData == null)
            return null;
        try {
            userDTO = new UserDto();
            userDTO.setUserId(userData.getUser().getId());
            userDTO.setUserDataId(userData.getId());
            userDTO.setUsername(userData.getUser().getUsername());
            userDTO.setPassword(userData.getUser().getPass());
            userDTO.setFirstName(userData.getFirstName());
            userDTO.setLastName(userData.getLastName());
            userDTO.setStreet(userData.getStreet());
            userDTO.setCity(userData.getStreet().getCity());
            userDTO.setCountry(userData.getStreet().getCity().getCountry());
            userDTO.setUserTariffs(userTariffDao.getByUserId(userData.getUser().getId()));
        } catch (DaoExceptions e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    public User toUserPOJO(UserDto userDTO, User user) {
        if (userDTO == null || user == null)
            return null;
        user.setUsername(userDTO.getUsername());
        user.setPass(userDTO.getPassword());
        return user;
    }

    public UserData toUserDataPOJO(UserDto userDto, UserData userData) throws DaoExceptions {
        if (userDto == null || userData == null)
            return null;
        User user = new User(userDto.getUsername(), userDto.getPassword());
        Street street = streetDao.getById(userDto.getStreet().getId());
        userData.setUser(user);
        userData.setFirstName(userDto.getFirstName());
        userData.setLastName(userDto.getLastName());
        userData.setStreet(street);
        userData.setCityId(street.getCity().getId());
        userData.setCountryId(street.getCity().getCountry().getId());

        return userData;
    }
}
