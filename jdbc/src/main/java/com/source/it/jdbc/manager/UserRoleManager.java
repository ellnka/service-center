package com.source.it.jdbc.manager;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.UserRole;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleManager {
    private static final String SELECT_BY_NAME = "SELECT UR.ID, UR.ROLE FROM USER_ROLES UR WHERE UR.ROLE=?";
    private DataSource dataSource;

    public UserRoleManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserRole getByRoleName(String role) {
        try (Connection con = dataSource.getConnection()) {
            UserRole userRole = new UserRole();
            PreparedStatement stmt = con.prepareStatement(SELECT_BY_NAME);
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
