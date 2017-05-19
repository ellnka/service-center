package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WarrantyPeriod extends BaseEntity<Long> {
    private int days;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setInt(2, days);
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public void setDataFromResultSet(ResultSet resultSet) throws SQLException {
        name = resultSet.getString(1);
        days = resultSet.getInt(2);
    }

    @Override
    public void prepareUpdateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setInt(   2, days);
        stmt.setLong(  3, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarrantyPeriod)) return false;

        WarrantyPeriod period = (WarrantyPeriod) o;

        if (period.days != days) {
            return false;
        }

        return  (period.name == null ? name == null : period.name.equals(name));
    }

}
