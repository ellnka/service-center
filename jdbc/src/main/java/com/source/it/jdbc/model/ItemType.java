package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemType extends BaseEntity<Long> {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemType)) return false;

        ItemType type = (ItemType) o;

        return  (type.name == null ? name == null : type.name.equals(name));
    }
}
