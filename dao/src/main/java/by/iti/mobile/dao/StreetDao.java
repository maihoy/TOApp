package by.iti.mobile.dao;

import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.Street;

import java.util.List;

/**
 * Created by j on 12.5.17.
 */
public interface StreetDao extends DAO<Street, Long> {
    List<Street> getByCityId (Long cityId) throws DaoExceptions;
}
