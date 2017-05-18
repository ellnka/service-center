package com.source.it.jdbc.dao;

public abstract class AbstractDaoFactory {
    public static AbstractDaoFactory getDaoFactory(Class type) {
        return new MySqlDaoFactory(type);
    }

    public abstract GenericDao getDao();
}
