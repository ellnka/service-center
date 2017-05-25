package com.source.it.services;


import com.source.it.jdbc.dao.AbstractDaoFactory;
import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.AbstractManagerFactory;
import com.source.it.jdbc.manager.UserManager;
import com.source.it.jdbc.model.User;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;

public class UserService {
    public static final Logger LOGGER = Logger.getLogger(GetClassUtil.getClassName());
    protected UserManager userManager = AbstractManagerFactory.getManagerFactory().getUserManager();
    private GenericDao<User, Long> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();

    public User getUserByLogin(String login) {
        LOGGER.debug("getUserByLogin was called with argument login = " + login);
        try {
            User user = userManager.getUserByLogin(login);
            LOGGER.debug("Get user " + user + "by login  " + login);
            return user;
        } catch (GenericDaoException e) {
            LOGGER.info("Didn't found any user by login  " + login, e);
            return null;
        }
    }

    public void createUser(User user) {
        userDao.create(user);
    }
}
