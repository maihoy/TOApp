package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.TariffDao;
import by.iti.mobile.pojo.Tariff;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by j on 22.5.17.
 */
@Repository("tariffDao")
public class TariffDaoImpl extends AbstractDao<Long,Tariff> implements TariffDao {
    private static Logger logger = Logger.getLogger(TariffDaoImpl.class);

    public TariffDaoImpl() {
        super();
    }
}
