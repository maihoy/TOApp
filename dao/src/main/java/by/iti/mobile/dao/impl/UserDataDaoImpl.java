package by.iti.mobile.dao.impl;

import by.iti.mobile.dao.AbstractDao;
import by.iti.mobile.dao.UserDataDao;
import by.iti.mobile.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("userDataDao")
public class UserDataDaoImpl extends AbstractDao<Long, UserData> implements UserDataDao {

    public UserDataDaoImpl() {
        super();
    }

    private static Logger logger = Logger.getLogger(UserDataDaoImpl.class);
}
