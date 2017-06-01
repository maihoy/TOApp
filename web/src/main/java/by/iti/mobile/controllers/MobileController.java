package by.iti.mobile.controllers;

import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.pojo.Service;
import by.iti.mobile.pojo.Tariff;
import by.iti.mobile.services.MobileService;
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
 * Created by j on 26.5.17.
 */
@RestController
public class MobileController {
    public static Logger logger = Logger.getLogger(MobileController.class);

    @Autowired
    MobileService mobileService;

    @RequestMapping(value = "/tariff", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tariff>> listAllTariffs() {
        List<Tariff> tariffs = null;
        try {
            tariffs = mobileService.getAllTariffs();
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (tariffs == null || tariffs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tariffs, HttpStatus.OK);
    }

    // Get by Id
    @RequestMapping(value = "/tariff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tariff> getTariff(@PathVariable("id") long id) {
        Tariff tariff = null;
        try {
            tariff = mobileService.getTariffById(id);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        if (tariff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tariff, HttpStatus.OK);
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Service>> listAllServices() {
        List<Service> services = null;
        try {
            services = mobileService.getAllServices();
        } catch (Exception e) {
            logger.error(e, e.getCause());
        }
        if (services == null || services.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    // Get by Id
    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Service> getService(@PathVariable("id") long id) {
        Service service = null;
        try {
            service = mobileService.getServiceById(id);
        } catch (ServiceException e) {
            logger.error(e, e.getCause());
        }
        if (service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service, HttpStatus.OK);
    }
}
