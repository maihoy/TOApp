package by.iti.mobile.dao;


import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by j on 14.4.17.
 */
public interface DAO<T extends AbstractEntity<K>, K extends Serializable> {
    K insert(T entity) throws DaoExceptions;

    T getById(K id) throws DaoExceptions;

    void update(T entity) throws DaoExceptions;

    void deleteById(K id) throws DaoExceptions;

    List<T> getAll() throws DaoExceptions;

    Long getCount() throws DaoExceptions;

    List<T> getByGap(int offset, int quantity) throws DaoExceptions;

}