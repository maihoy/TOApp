package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.CountryDao;
import by.iti.mobile.pojo.Country;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by j on 12.5.17.
 */
@Repository("countryDao")
public class CountryDaoImpl extends AbstractDao<Long, Country> implements CountryDao {

    private static Logger logger = Logger.getLogger(CountryDaoImpl.class);

    public CountryDaoImpl() {
        super();
    }
}
