package com.source.it.services;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.UserRoleManager;
import com.source.it.jdbc.model.UserRole;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleService {
    public static final Logger LOGGER = Logger.getLogger(GetClassUtil.getClassName());
    @Autowired
    private UserRoleManager manager;

    public UserRole getUserRoleByRoleName(String name) {
        try {
            return manager.getByRoleName(name);
        } catch (GenericDaoException e) {
            LOGGER.info("Didn't found any user role by rolename   " + name, e);
            return null;
        }
    }
}
