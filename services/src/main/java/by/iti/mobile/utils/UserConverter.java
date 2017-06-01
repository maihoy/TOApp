package by.iti.mobile.utils;

import by.iti.mobile.dao.*;
import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class UserConverter {

    Logger logger = Logger.getLogger(UserConverter.class);

    @Autowired
    UserDao userDao;
    @Autowired
    StreetDao streetDao;
    @Autowired
    UserTariffDao userTariffDao;
    @Autowired
    TariffDao tariffDao;
    @Autowired
    ServiceDao serviceDao;

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

    public UserTariff toUserTariffPOJO(Long userId, UserTariff userTariff) throws DaoExceptions {
        if (userTariff == null)
            return null;
        userTariff.setUser(userDao.getById(userId));
        userTariff.setAccBalance(0.0);
        userTariff.setPhoneNum(userTariff.getPhoneNum());
        logger.info(userTariff);
        userTariff.setTariff(tariffDao.getById(userTariff.getTariff().getId()));
        Set<Service> services = new HashSet<>();
        for (Service service : userTariff.getService()) {
            services.add(serviceDao.getById(service.getId()));
        }
        userTariff.setService(services);
        return userTariff;
    }
}
