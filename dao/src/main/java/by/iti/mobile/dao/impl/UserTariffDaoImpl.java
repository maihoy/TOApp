package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.UserTariffDao;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.UserTariff;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by j on 22.5.17.
 */
@Repository("userTariffDao")
public class UserTariffDaoImpl extends AbstractDao<Long, UserTariff> implements UserTariffDao {

    private static Logger logger = Logger.getLogger(UserTariffDaoImpl.class);

    public UserTariffDaoImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<UserTariff> getByUserId(Long id) throws DaoExceptions {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        List<UserTariff> list = (List<UserTariff>) criteria.list();
        Set<UserTariff> set = new HashSet<>();
        for (UserTariff userTariff : list) {
            set.add(userTariff);
        }
        return set;
    }

    @Override
    public List<Long> getAllIds() throws DaoExceptions {
        Session session = getSession();
        Query query = session.createSQLQuery("SELECT id FROM user_tariff");
        List<Long> list= new ArrayList<>();
        for (Object o:query.list()){
            list.add(((Integer) o).longValue());
        }
        return list;
    }
}
