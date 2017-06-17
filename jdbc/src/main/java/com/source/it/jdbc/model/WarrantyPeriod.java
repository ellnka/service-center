package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WarrantyPeriod extends BaseEntity<Long> {
    private int days;
    private String wpName;

    public String getWpName() {
        return wpName;
    }

    public void setWpName(String wpName) {
        this.wpName = wpName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setInt(1, days);
        stmt.setString(2, wpName);
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarrantyPeriod)) return false;

        WarrantyPeriod period = (WarrantyPeriod) o;

        if (period.days != days) {
            return false;
        }

        return  (period.wpName == null ? wpName == null : period.wpName.equals(wpName));
    }

    @Override
    public String toString() {
        return "WarrantyPeriod{" +
                "days=" + days +
                ", wpName='" + wpName + '\'' +
                '}';
    }
}
