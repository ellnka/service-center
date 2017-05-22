package com.source.it.services;


import com.source.it.jdbc.dao.AbstractDaoFactory;
import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.AbstractManagerFactory;
import com.source.it.jdbc.manager.UserManager;
import com.source.it.jdbc.model.User;

public class UserService {
    protected UserManager userManager = AbstractManagerFactory.getManagerFactory().getUserManager();
    private GenericDao<User, Long> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();

    public User getUserByLogin(String login) {
        try {
            return userManager.getUserByLogin(login);
        } catch (GenericDaoException e) {
            return null;
        }
    }

    public void createUser(User user) {
        userDao.create(user);
    }
}
