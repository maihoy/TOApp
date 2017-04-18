package by.iti.mobile;


import by.iti.mobile.constants.DaoConstants;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by j on 14.4.17.
 */
public abstract class AbstractDao<K extends Serializable, T extends AbstractEntity<K>> implements DAO<T, K> {
    @Autowired
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(AbstractDao.class);
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass=(Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    @Override
    public K insert(T entity) throws DaoExceptions {
        K id;
        try {
            Session session = getSession();
            session.saveOrUpdate(entity);
            id = (K) session.getIdentifier(entity);
        }
        catch (HibernateException e) {
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions();
        }
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(K id) throws DaoExceptions {
        T entity;
        if (id==null){
            return null;
        }else {
            try {
                Session session = getSession();
                entity = (T) session.get(persistentClass, id);
            } catch (HibernateException e) {
                logger.error(DaoConstants.ERROR_DAO, e);
                throw new DaoExceptions(e);
            }
            return entity;
        }}

    @Override
    public void update(T entity) throws DaoExceptions {
        try {
            Session session = getSession();
            session.merge(entity);
        }
        catch(HibernateException e) {
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions();
        }
    }

    @Override
    public void deleteById(K id) throws DaoExceptions {
        try {
            Session session = getSession();
            T entity = (T) session.get(persistentClass, id);
            session.delete(entity);
        } catch (HibernateException | IllegalArgumentException e) {
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions(e);
        }
    }

    @Override
    public List<T> getAll() throws DaoExceptions {
        List<T> entities;
        try {
            entities = (List<T>) createEntityCriteria().list();
        } catch (HibernateException e) {
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions(e);
        }
        return entities;
    }

    @Override
    public Long getCount() throws DaoExceptions {
        Long result;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(persistentClass);
            Projection count = Projections.rowCount();
            criteria.setProjection(count);
            result = (Long) criteria.uniqueResult();
        }
        catch(HibernateException e) {
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions();
        }
        return result;
    }


    @Override
    public List<T> getByGap(int offset, int quantity) throws DaoExceptions {
        List<T> results;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(persistentClass);
            criteria.setFirstResult(offset);
            criteria.setMaxResults(quantity);
            results = criteria.list();
        }
        catch(HibernateException e){
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions();
        }
        return results;
    }
}
