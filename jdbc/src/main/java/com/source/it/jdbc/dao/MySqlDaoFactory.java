package com.source.it.jdbc.dao;

import com.source.it.jdbc.model.BaseEntity;

public class MySqlDaoFactory<T extends BaseEntity> extends AbstractDaoFactory {
    private Class<T> type;

    protected MySqlDaoFactory(Class<T> type) {
        this.type = type;
    }

    @Override
    public GenericDao getDao() {
        return new GenericDaoImpl(type);
    }

}
