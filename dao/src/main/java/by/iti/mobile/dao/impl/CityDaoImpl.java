package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.CityDao;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.pojo.City;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by j on 12.5.17.
 */
@Repository("cityDao")
public class CityDaoImpl extends AbstractDao<Long, City> implements CityDao {
    private static Logger logger = Logger.getLogger(CityDaoImpl.class);

    public CityDaoImpl() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<City> getByCountryId(Long countryId) throws DaoExceptions {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("country.id", countryId));
        return (List<City>) criteria.list();
    }
}
