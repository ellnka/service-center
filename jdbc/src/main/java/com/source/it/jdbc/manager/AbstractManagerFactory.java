package com.source.it.jdbc.manager;

public abstract class AbstractManagerFactory {
    public static AbstractManagerFactory getManagerFactory() {
        return new MySqlManagerFactory();
    }

    public abstract UserManager getUserManager();

    public abstract UserRoleManager getUserRoleManager();

    public abstract ManufactureManager getManufactureManager();

    public abstract WarrantyPeriodManager getWarrantyPeriodManager();

    public abstract ItemTypeManager getItemTypeManager();

    public abstract ItemManager getItemManager();

    public abstract OrderManager getOrderManager();
}
