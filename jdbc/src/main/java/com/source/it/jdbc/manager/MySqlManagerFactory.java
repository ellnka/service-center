package com.source.it.jdbc.manager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.source.it.jdbc.exceptions.GenericDaoException;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySqlManagerFactory extends AbstractManagerFactory {
    private static final String DB_USER                 = "db.user";
    private static final String DB_PASSWORD             = "db.password";
    private static final String DB_PORT                 = "db.port";
    private static final String DB_HOST                 = "db.host";
    private static final String DB_SCHEMA               = "db.schema";
    private DataSource dataSource;

    public MySqlManagerFactory() {
        dataSource = createDataSourceAndLoadProperties();
    }

    @Override
    public UserManager getUserManager() {
        return new UserManager(dataSource);
    }

    @Override
    public UserRoleManager getUserRoleManager() {
        return new UserRoleManager(dataSource);
    }

    private DataSource createDataSourceAndLoadProperties() {
        Properties props = new Properties();
        MysqlDataSource dataSource = new MysqlDataSource();
        try {
            FileInputStream fis = new FileInputStream(
                    new File(getClass().getClassLoader().getResource("db.properties").getFile()));
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
