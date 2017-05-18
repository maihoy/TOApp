package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.StreetDao;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.Street;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by j on 12.5.17.
 */
@Repository("streetDao")
public class StreetDaoImpl extends AbstractDao<Long, Street> implements StreetDao {
    private static Logger logger = Logger.getLogger(StreetDaoImpl.class);

    public StreetDaoImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Street> getByCityId(Long cityId) throws DaoExceptions {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("city.id", cityId));
        return (List<Street>) criteria.list();
    }
}
