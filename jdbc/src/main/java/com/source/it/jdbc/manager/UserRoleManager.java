package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.UserRole;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.generateSelectByFieldSql;

public class UserRoleManager {
    private static final Logger LOGGER = Logger.getLogger(UserManager.class);
    private DataSource dataSource;

    public UserRoleManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserRole getByRoleName(String role) {
        try (Connection con = dataSource.getConnection()) {
            UserRole userRole = new UserRole();
            String sql = generateSelectByFieldSql(userRole, "role");
            LOGGER.debug("Created sql for UserRole:" + sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, role);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                userRole.setId(resultSet.getLong("ID"));
                userRole.setRole(resultSet.getString("ROLE"));
                if (resultSet.next()) {
                    throw new GenericDaoException("More than 1 user roles was found with unique field name = " + role);
                }
                return userRole;
            }
        } catch (SQLException e) {
            throw new GenericDaoException("Error reading user from DB");
        }
        throw new GenericDaoException("Error reading "
                +  " user roles from DB - no user roles was found with role = " + role);
    }

}
