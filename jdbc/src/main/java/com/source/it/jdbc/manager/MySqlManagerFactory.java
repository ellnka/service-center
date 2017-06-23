package com.source.it.jdbc.manager;

import org.springframework.stereotype.Component;

@Component
public class MySqlManagerFactory extends AbstractManagerFactory {

    @Override
    public UserManager getUserManager() {
        return new UserManager();
    }

    @Override
    public UserRoleManager getUserRoleManager() {
        return new UserRoleManager();
    }

    @Override
    public ManufactureManager getManufactureManager() {
        return new ManufactureManager();
    }

    @Override
    public WarrantyPeriodManager getWarrantyPeriodManager() {
        return new WarrantyPeriodManager();
    }

    @Override
    public ItemTypeManager getItemTypeManager() {
        return new ItemTypeManager();
    }

    @Override
    public ItemManager getItemManager() {
        return new ItemManager();
    }

    @Override
    public OrderManager getOrderManager() {
        return new OrderManager();
    }


}
