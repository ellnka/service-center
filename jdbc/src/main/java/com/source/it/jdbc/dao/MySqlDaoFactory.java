package com.source.it.jdbc.dao;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.source.it.jdbc.model.BaseEntity;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.utils.GetClassUtil;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MySqlDaoFactory<T extends BaseEntity> extends AbstractDaoFactory {
    public final static Logger LOGGER                   = Logger.getLogger(GetClassUtil.getClassName());
    private static final String DB_USER                 = "db.user";
    private static final String DB_PASSWORD             = "db.password";
    private static final String DB_PORT                 = "db.port";
    private static final String DB_HOST                 = "db.host";
    private static final String DB_SCHEMA               = "db.schema";
    private File dbPropsFile;

    private Class<T> type;
    protected DataSource dataSource;

    protected MySqlDaoFactory(Class<T> type) {
        dbPropsFile = new File(getClass().getClassLoader().getResource("db.properties").getFile());
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
            FileInputStream fis = new FileInputStream(dbPropsFile);
            props.load(fis);
            dataSource.setUser(                 props.getProperty(DB_USER    ));
            dataSource.setPassword(             props.getProperty(DB_PASSWORD));
            dataSource.setPort(Integer.parseInt(props.getProperty(DB_PORT    )));
            dataSource.setServerName(           props.getProperty(DB_HOST    ));
            dataSource.setDatabaseName(         props.getProperty(DB_SCHEMA  ));
            LOGGER.debug("Properties loaded and set to datasource" + props);

        } catch (IOException e) {
            LOGGER.error("Error loading properties ", e);
            throw new GenericDaoException("Error loading properties", e);
        }
        return dataSource;
    }
}
