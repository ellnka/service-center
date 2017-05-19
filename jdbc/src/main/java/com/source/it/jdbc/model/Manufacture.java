package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manufacture extends BaseEntity<Long> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void setDataFromResultSet(ResultSet resultSet) throws SQLException {
        name = resultSet.getString(1);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setLong(  2, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacture)) return false;

        Manufacture manufacture = (Manufacture) o;

        return  (manufacture.name == null ? name == null : manufacture.name.equals(name));
    }
}
