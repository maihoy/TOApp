package by.iti.mobile.services.impl;


import by.iti.mobile.dto.UserDto;
import by.iti.mobile.enums.UserRole;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDTO = null;
        boolean enabled;
        UserDetails result;
        try {
            userDTO = userService.getByUsername(username);
        } catch (ServiceException e) {
            //todo
            e.printStackTrace();
        }
        if (userDTO == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        //enabled = userDTO.getStatus().equals(UserStatus.ONLINE) || userDTO.getStatus().equals(UserStatus.OFLINE);
        result = new org.springframework.security.core.userdetails.User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(userDTO));
        return result;
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDTO) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
                "ROLE_" + UserRole.valueOf(String.valueOf(userDTO.getRole())));
        authorities.add(authority);
        return authorities;
    }
}
