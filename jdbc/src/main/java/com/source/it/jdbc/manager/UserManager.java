package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class UserManager {
    private static final Logger LOGGER = Logger.getLogger(UserManager.class);

    @Autowired
    protected DataSource dataSource;

    public User getUserByLogin(String login) {
        try (Connection con = dataSource.getConnection()) {
            User user = new User();
            String sql = generateSelectByFieldSql(user, "LOGIN");
            LOGGER.debug("Created sql for User:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, user);
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception during connection to db - ", e);
            throw new GenericDaoException("Error reading user from DB");
        }
        LOGGER.info("Error reading "
                +  " user from DB - no user was found with login = " + login);
       throw new GenericDaoException("Error reading "
                +  " user from DB - no user was found with login = " + login);
    }

}
