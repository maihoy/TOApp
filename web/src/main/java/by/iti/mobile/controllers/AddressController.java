package by.iti.mobile.controllers;

import by.iti.mobile.dto.AddressDto;
import by.iti.mobile.services.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by j on 18.5.17.
 */
@RestController
public class AddressController {
    public static Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/city-parent-{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDto>> listAllCitiesByCountry(@PathVariable("id") long id) {
        List<AddressDto> addressDtos = null;
        try {
            addressDtos = addressService.getAllCitesByCountryId(id);
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (addressDtos == null || addressDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/street-parent-{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDto>> listAllStreetsByCityId(@PathVariable("id") long id) {
        List<AddressDto> addressDtos = null;
        try {
            addressDtos = addressService.getAllStreetsByCityId(id);
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (addressDtos == null || addressDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AddressDto>> listAllCounties() {
        List<AddressDto> addressDtos = null;
        try {
            addressDtos = addressService.getAllCounties();
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (addressDtos == null || addressDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }
}
