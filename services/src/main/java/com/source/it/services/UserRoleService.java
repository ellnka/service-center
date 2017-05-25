package com.source.it.services;

import com.source.it.jdbc.manager.AbstractManagerFactory;
import com.source.it.jdbc.manager.UserRoleManager;
import com.source.it.jdbc.model.UserRole;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;

public class UserRoleService {
    public static final Logger LOGGER = Logger.getLogger(GetClassUtil.getClassName());
    private UserRoleManager manager = AbstractManagerFactory.getManagerFactory().getUserRoleManager();

    public UserRole getUserRoleByRoleName(String name) {
        return manager.getByRoleName(name);
    }
}
