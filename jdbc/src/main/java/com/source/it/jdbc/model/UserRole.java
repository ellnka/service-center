package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRole extends BaseEntity<Long> {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, role);
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void setDataFromResultSet(ResultSet resultSet) throws SQLException {
        role = resultSet.getString(2);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, role);
        stmt.setLong  (2, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;

        UserRole userRole = (UserRole) o;

        return !(role != null ? !role.equals(userRole.role) : userRole.role != null);

    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                "role='" + role + '\'' +
                '}';
    }
}
