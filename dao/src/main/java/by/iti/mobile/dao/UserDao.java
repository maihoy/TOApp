package by.iti.mobile.dao;

import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.User;

/**
 * Created by j on 14.4.17.
 */
public interface UserDao extends DAO<User, Long> {
    User getByUsername(String username) throws DaoExceptions;

    User getByUsernamePassword(String username, String password) throws DaoExceptions;
}
