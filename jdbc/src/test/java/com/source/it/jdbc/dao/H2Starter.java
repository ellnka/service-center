package com.source.it.jdbc.dao;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class H2Starter {
    private static final String H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL";
    private static Server server;


    public static void startH2() throws SQLException {
        server =  Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers");
        server.start();
        String content = null;
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(H2_URL);
        dataSource.setUser("sa");
        ClassLoader classLoader = server.getClass().getClassLoader();
        File file = new File(classLoader.getResource("create_db.sql").getFile());
        try {
            content = new Scanner(file).useDelimiter("\\Z").next();
            Connection con = dataSource.getConnection();
            con.createStatement().executeUpdate(content);
            con.close();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void shutDownH2() {
        server.stop();
    }
}
