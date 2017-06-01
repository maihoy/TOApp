package by.iti.mobile.controllers;


import by.iti.mobile.dto.UserDto;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.services.AddressService;
import by.iti.mobile.services.MobileService;
import by.iti.mobile.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by j on 19.4.17.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    MobileService mobileService;

    private static Logger logger = Logger.getLogger(UserController.class);
    private static final int RECORDS_PER_PAGE = 3;

    @RequestMapping(value = "/user", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<UserDto> userDTOs = null;
        try {
            userDTOs = userService.getAll();
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (userDTOs == null || userDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/pages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getPageCount() {
        int pagesCount = 1;
        try {
            Long countRecords = userService.getCount();
            pagesCount = (int) Math.ceil(Double.valueOf(countRecords)/ (double) RECORDS_PER_PAGE) ;
            logger.debug(Math.ceil(Double.valueOf(countRecords)/ (double) RECORDS_PER_PAGE));
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        return new ResponseEntity<>(pagesCount, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/page-{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getUserPerPage(@PathVariable("page") int page) {
        List<UserDto> userDtos = null;
        int pageNum = page;
        try {
            userDtos = userService.getByGap((pageNum - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    // Get by Id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        UserDto userDto = null;
        try {
            userDto = userService.getById(id);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    //Create
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {
        try {
            logger.info(userDto.toString());
            userService.insert(userDto);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userDto.getUserDataId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //update
    @RequestMapping(value = "/user/{userDataId}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUser(@PathVariable("userDataId") long userDataId, @RequestBody UserDto userDto) {
        UserDto currentUser = null;
        try {
            logger.info(userDto);
//            currentUser = userService.getById(userDataId);
//            currentUser.setUserId(userDto.getUserId());
//            currentUser.setUsername(userDto.getUsername());
//            currentUser.setPassword(userDto.getPassword());
//            currentUser.setUserDataId(userDto.getUserDataId());
//            currentUser.setStreet(addressService.getStreetById(userDto.getStreet().getId()).getStreet());
//            currentUser.setFirstName(userDto.getFirstName());
//            currentUser.setLastName(userDto.getLastName());
//            currentUser.setCity(currentUser.getStreet().getCity());
//            currentUser.setCountry(currentUser.getStreet().getCity().getCountry());
//
//            for (UserTariff userTariff: userDto.getUserTariffs()) {
//
//                currentUser.getUserTariffs().clear();
//                userTariff.setUser(mobileService.getUserTariffById(userTariff.getId()).getUser());
//                userTariff.setTariff(mobileService.getTariffById(userTariff.getTariff().getId()));
//                Set<Service> services = new HashSet<>();
//                for (Service service: userTariff.getService()){
//                    services.add(mobileService.getServiceById(service.getId()));
//                }
//                userTariff.setService(services);
//                currentUser.addUserTariff(userTariff);
//            }

            userService.insert(userDto);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") long id) {
        UserDto userDto;
        try {
            userDto = userService.getById(id);
            if (userDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            userService.deleteById(id);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
