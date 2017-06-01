package by.iti.mobile.dao;

import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.UserTariff;

import java.util.List;
import java.util.Set;

/**
 * Created by j on 22.5.17.
 */
public interface UserTariffDao extends DAO<UserTariff,Long> {
    Set<UserTariff> getByUserId (Long id) throws DaoExceptions;

    List<Long> getAllIds () throws DaoExceptions;
}
