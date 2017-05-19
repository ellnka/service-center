package com.source.it.jdbc.dao;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.source.it.jdbc.model.BaseEntity;
import com.source.it.jdbc.exceptions.GenericDaoException;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySqlDaoFactory<T extends BaseEntity> extends AbstractDaoFactory {
    private static final String RESOURCES_DB_PROPERTIES = "/Users/kblyumkin/Projects/SourceIt/src/com/kblyumkin/lecture15/examples/resources/db.properties";
    private static final String DB_USER                 = "db.user";
    private static final String DB_PASSWORD             = "db.password";
    private static final String DB_PORT                 = "db.port";
    private static final String DB_HOST                 = "db.host";
    private static final String DB_SCHEMA               = "db.schema";

    private Class<T> type;
    protected DataSource dataSource;

    protected MySqlDaoFactory(Class<T> type) {
        this.type = type;
        this.dataSource = createDataSourceAndLoadProperties();
    }

    @Override
    public GenericDao getDao() {
        return new GenericDaoImpl(dataSource, type);
    }

    private DataSource createDataSourceAndLoadProperties() {
        Properties props = new Properties();
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            FileInputStream fis = new FileInputStream(
                    new File(RESOURCES_DB_PROPERTIES));
            props.load(fis);
            dataSource.setUser(                 props.getProperty(DB_USER    ));
            dataSource.setPassword(             props.getProperty(DB_PASSWORD));
            dataSource.setPort(Integer.parseInt(props.getProperty(DB_PORT    )));
            dataSource.setServerName(           props.getProperty(DB_HOST    ));
            dataSource.setDatabaseName(         props.getProperty(DB_SCHEMA  ));

        } catch (IOException e) {
            throw new GenericDaoException("Error loading properties", e);
        }
        return dataSource;
    }
}
