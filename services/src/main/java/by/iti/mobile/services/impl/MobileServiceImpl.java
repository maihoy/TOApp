package by.iti.mobile.services.impl;

import by.iti.mobile.dao.ServiceDao;
import by.iti.mobile.dao.TariffDao;
import by.iti.mobile.dao.UserTariffDao;
import by.iti.mobile.exceptions.DaoExceptions;
import by.iti.mobile.exceptions.ServiceException;
import by.iti.mobile.pojo.Service;
import by.iti.mobile.pojo.Tariff;
import by.iti.mobile.pojo.UserTariff;
import by.iti.mobile.services.MobileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by j on 26.5.17.
 */
@org.springframework.stereotype.Service("mobileService")
@Transactional
public class MobileServiceImpl implements MobileService {

    public static Logger logger = Logger.getLogger(MobileServiceImpl.class);

    @Autowired
    ServiceDao serviceDao;
    @Autowired
    TariffDao tariffDao;
    @Autowired
    UserTariffDao userTariffDao;

    @Override
    public List<Service> getAllServices() throws ServiceException {
        List<Service> services = null;
        try {
            services = serviceDao.getAll();
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return services;
    }

    @Override
    public Service getServiceById(Long id) throws ServiceException {
        Service service = null;
        try {
            service = serviceDao.getById(id);
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return service;
    }

    @Override
    public List<Tariff> getAllTariffs() throws ServiceException {
        List<Tariff> tariffs = null;
        try {
            tariffs = tariffDao.getAll();
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return tariffs;
    }

    @Override
    public Tariff getTariffById(Long id) throws ServiceException {
        Tariff tariff = null;
        try {
            tariff = tariffDao.getById(id);
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return tariff;
    }

    @Override
    public UserTariff getUserTariffById(Long id) throws ServiceException {
        UserTariff userTariff = null;
        try {
            userTariff = userTariffDao.getById(id);
        } catch (DaoExceptions e) {
            logger.error(e);
        }
        return userTariff;
    }
}
