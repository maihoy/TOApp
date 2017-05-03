package by.iti.mobile.utils;

import by.iti.mobile.dto.UserDto;
import by.iti.mobile.pojo.User;
import org.springframework.stereotype.Component;

/**
 * Created by j on 18.4.17.
 */
@Component
public class UserConverter {
    public UserDto toUserDTO(User user) {
        UserDto userDTO;
        if (user == null)
            return null;
        userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPass());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public User toUserPOJO(UserDto userDTO, User user) {
        if (userDTO == null || user == null)
            return null;
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPass(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }
}
