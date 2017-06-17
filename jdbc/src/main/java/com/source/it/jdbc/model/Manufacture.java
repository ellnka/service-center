package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Manufacture extends BaseEntity<Long> {
    private String manufactureName;

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, manufactureName);
    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {
        stmt.setLong(1, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacture)) return false;

        Manufacture manufacture = (Manufacture) o;

        return  (manufacture.manufactureName == null ? manufactureName == null : manufacture.manufactureName.equals(manufactureName));
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "manufactureName='" + manufactureName + '\'' +
                '}';
    }
}
