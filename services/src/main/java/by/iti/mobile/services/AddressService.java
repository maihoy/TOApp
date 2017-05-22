package by.iti.mobile.services;

import by.iti.mobile.dto.AddressDto;
import by.iti.mobile.exceptions.ServiceException;

import java.util.List;

/**
 * Created by j on 18.5.17.
 */
public interface AddressService {

    List<AddressDto> getAllCitesByCountryId(Long countryId) throws ServiceException;

    List<AddressDto> getAllStreetsByCityId(Long cityId) throws ServiceException;

    List<AddressDto> getAllCounties() throws ServiceException;

    AddressDto getStreetById(Long id) throws ServiceException;

}
