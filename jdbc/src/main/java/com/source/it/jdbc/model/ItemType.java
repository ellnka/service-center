package com.source.it.jdbc.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemType extends BaseEntity<Long> {
    private String itemTypeName;

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypename) {
        this.itemTypeName = itemTypename;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, itemTypeName);
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

        return  (type.itemTypeName == null ? itemTypeName == null : type.itemTypeName.equals(itemTypeName));
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "itemTypeName='" + itemTypeName + '\'' +
                '}';
    }
}
