package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.utils.SqlGeneratorUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class UserManager {
    //private final static String SELECT_BY_LOGIN = "SELECT U.ID, U.NAME, U.LASTNAME, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, UR.ROLE FROM USERS U JOIN USER_ROLES UR ON U.USER_ROLE_ID = UR.ID WHERE U.LOGIN = ?";
    private DataSource dataSource;

    public UserManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserByLogin(String login) {
        try (Connection con = dataSource.getConnection()) {
            User user = new User();
            PreparedStatement stmt = con.prepareStatement(generateSelectByFieldSql(user, "LOGIN"));
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                SqlGeneratorUtils.fillObjectFromResultSet(resultSet, user);
                return user;
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error reading user from DB");
        }
       throw new GenericDaoException("Error reading "
                +  " user from DB - no user was found with login = " + login);
    }

}
