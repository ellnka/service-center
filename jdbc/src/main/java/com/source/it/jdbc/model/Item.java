package com.source.it.jdbc.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Item extends BaseEntity<Long> {
    private Manufacture manufacture;
    private ItemType itemType;
    private WarrantyPeriod warrantyPeriod;
    private Date dateOfSale;
    private String serialNumber;

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public WarrantyPeriod getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(WarrantyPeriod warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public void prepareCreateStatement(PreparedStatement stmt) throws SQLException {

    }

    @Override
    public void prepareReadOrDeleteStatement(PreparedStatement stmt, Long id) throws SQLException {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (manufacture != null ? !manufacture.equals(item.manufacture) : item.manufacture != null) return false;
        if (itemType != null ? !itemType.equals(item.itemType) : item.itemType != null) return false;
        if (warrantyPeriod != null ? !warrantyPeriod.equals(item.warrantyPeriod) : item.warrantyPeriod != null)
            return false;
        if (dateOfSale != null ? !dateOfSale.equals(item.dateOfSale) : item.dateOfSale != null) return false;
        return !(serialNumber != null ? !serialNumber.equals(item.serialNumber) : item.serialNumber != null);

    }
}
