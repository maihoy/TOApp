package by.iti.mobile.services.impl;

import by.iti.mobile.dao.CityDao;
import by.iti.mobile.dao.CountryDao;
import by.iti.mobile.dao.StreetDao;
import by.iti.mobile.dto.AddressDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.pojo.City;
import by.iti.mobile.pojo.Country;
import by.iti.mobile.pojo.Street;
import by.iti.mobile.services.AddressService;
import by.iti.mobile.utils.AddressConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 18.5.17.
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    Logger logger =  Logger.getLogger(AddressServiceImpl.class);

    @Autowired
    CityDao cityDao;

    @Autowired
    StreetDao streetDao;

    @Autowired
    CountryDao countryDao;

    @Autowired
    AddressConverter converter;

    @Override
    public List<AddressDto> getAllCitesByCountryId(Long countryId) throws ServiceException {
        List<City> cities;
        List<AddressDto> addressDtos = new ArrayList<>();
        try {
            cities = cityDao.getByCountryId(countryId);
            for (City city:cities){
                addressDtos.add(converter.toAddressDto(null,city,null));
            }

        } catch (DaoExceptions daoExceptions) {
            logger.error(daoExceptions);
        }
        return addressDtos;
    }

    @Override
    public List<AddressDto> getAllStreetsByCityId(Long cityId) throws ServiceException {
        List<Street> streets;
        List<AddressDto> addressDtos = new ArrayList<>();
        try {
            streets = streetDao.getByCityId(cityId);
            for (Street street:streets){
                addressDtos.add(converter.toAddressDto(street,null,null));
            }
        } catch (DaoExceptions daoExceptions) {
            logger.error(daoExceptions);
        }
        return addressDtos;
    }

    @Override
    public List<AddressDto> getAllCounties() throws ServiceException {
        List<AddressDto> addressDtos = new ArrayList<>();
        try {
            List<Country> countries=countryDao.getAll();
            for (Country country:countries){
                addressDtos.add(converter.toAddressDto(null,null,country));
            }
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return addressDtos;
    }

    @Override
    public AddressDto getStreetById(Long id) throws ServiceException {
        AddressDto addressDto = null;
        try {
            addressDto = new AddressDto(null,null,streetDao.getById(id));
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return addressDto;
    }
}
