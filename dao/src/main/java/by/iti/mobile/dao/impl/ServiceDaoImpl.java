package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.ServiceDao;
import by.iti.mobile.pojo.Service;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by j on 22.5.17.
 */
@Repository("serviceDao")
public class ServiceDaoImpl extends AbstractDao<Long, Service> implements ServiceDao {
    private static Logger logger = Logger.getLogger(ServiceDaoImpl.class);

    public ServiceDaoImpl() {
        super();
    }
}
