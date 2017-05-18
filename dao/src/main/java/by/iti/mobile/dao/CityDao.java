package by.iti.mobile.dao;

import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.City;

import java.util.List;

/**
 * Created by j on 12.5.17.
 */
public interface CityDao extends DAO<City,Long> {
    List<City> getByCountryId (Long countryId) throws DaoExceptions;
}

