package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.UserTariffDao;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.UserTariff;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by j on 22.5.17.
 */
@Repository("userTariffDao")
public class UserTariffDaoImpl extends AbstractDao<Long,UserTariff> implements UserTariffDao {

    private static Logger logger = Logger.getLogger(UserTariffDaoImpl.class);

    public UserTariffDaoImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserTariff> getByUserId(Long id) throws DaoExceptions {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user.id", id));
        return (List<UserTariff>) criteria.list();
    }
}
