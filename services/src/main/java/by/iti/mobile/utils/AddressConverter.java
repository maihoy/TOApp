package by.iti.mobile.utils;

import by.iti.mobile.dao.CityDao;
import by.iti.mobile.dao.StreetDao;
import by.iti.mobile.dto.AddressDto;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.City;
import by.iti.mobile.pojo.Country;
import by.iti.mobile.pojo.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by j on 18.5.17.
 */
@Component
public class AddressConverter {

    @Autowired
    StreetDao streetDao;

    @Autowired
    CityDao cityDao;

    public AddressDto toAddressDto (Street street, City city, Country country) {
        AddressDto addressDto;
        if ((street == null)&&(city == null)&&(country==null))
            return null;
        addressDto = new AddressDto();
        addressDto.setCountry(country);
        addressDto.setCity(city);
        addressDto.setStreet(street);
        return addressDto;
    }

    public Street toStreetPojo (AddressDto addressDto) throws DaoExceptions {
        if (addressDto == null)
            return null;
        Street street = streetDao.getById(addressDto.getCity().getId());
        return street;
    }

    public City toCityPojo (AddressDto addressDto) throws DaoExceptions{
        if (addressDto == null)
            return null;
        City city = cityDao.getById(addressDto.getCity().getId());
        return city;
    }
}
