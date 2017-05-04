package com.source.it.services;

import jdbc.dao.AbstractDaoFactory;
import jdbc.dao.GenericDao;
import jdbc.exceptions.GenericDaoException;
import jdbc.model.UserRole;

public class UserService {
    public UserRole getUserRole(Long id) {
        GenericDao<UserRole, Long> userRoleDao =
                AbstractDaoFactory.getDaoFactory(UserRole.class).getDao();
        try {
            UserRole result = userRoleDao.read(id);
            return result;
        } catch (GenericDaoException e) {
            return null;
        }
    }

}
