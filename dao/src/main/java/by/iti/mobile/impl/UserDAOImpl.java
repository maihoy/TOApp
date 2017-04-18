package by.iti.mobile.impl;

import by.iti.mobile.AbstractDao;
import by.iti.mobile.UserDao;
import by.iti.mobile.constants.DaoConstants;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDAOImpl extends AbstractDao<Long,User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(){super();}

    @Override
    public User getByUsername(String username) throws DaoExceptions {
        User user;
        try {
            Session session = getSession();
            Query query = session.createSQLQuery("SELECT * FROM user WHERE username=:username")
                    .addEntity(User.class)
                    .setParameter("username", username);
            user = (User) query.uniqueResult();
        }
        catch(HibernateException e){
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions(e);
        }
        return user;
    }

    @Override
    public User getByUsernamePassword(String username, String password) throws DaoExceptions {
        User user;
        try {
            Session session = getSession();
            Query query = session.createSQLQuery("SELECT * FROM user WHERE username=:username AND pass=:password")
                    .addEntity(User.class)
                    .setParameter("username", username)
                    .setParameter("password", password);
            user = (User) query.uniqueResult();
        }
        catch(HibernateException e){
            logger.error(DaoConstants.ERROR_DAO, e);
            throw new DaoExceptions(e);
        }
        return user;    }
}
