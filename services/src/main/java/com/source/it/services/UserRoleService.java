package com.source.it.services;

import com.source.it.jdbc.manager.AbstractManagerFactory;
import com.source.it.jdbc.manager.UserRoleManager;
import com.source.it.jdbc.model.UserRole;

public class UserRoleService {
    private UserRoleManager manager = AbstractManagerFactory.getManagerFactory().getUserRoleManager();

    public UserRole getUserRoleByRoleName(String name) {
        return manager.getByRoleName(name);
    }
}
