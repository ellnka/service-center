package com.source.it.jdbc;

import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.model.*;

import java.sql.Date;

public class GenericDaoTestUtils {

    private GenericDaoTestUtils(){}

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("mike@mysite.com");
        user.setLastName("Smith");
        user.setLogin("mike");
        user.setName("Mike");
        user.setPassword("123");
        return user;
    }

    public static Item createItem(GenericDao itemTypeDao, GenericDao manufactureDao, GenericDao warrantyPeriodDao) {
        ItemType itemType = new ItemType();
        itemType.setItemTypeName("Refrigerator");
        itemTypeDao.create(itemType);

        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureName("Whirepool");
        manufactureDao.create(manufacture);

        WarrantyPeriod wp = new WarrantyPeriod();
        wp.setDays(180);
        wp.setWpName("Half of the year");
        warrantyPeriodDao.create(wp);

        Item item = new Item();
        item.setDateOfSale(new Date(System.currentTimeMillis() - (1000L * 60L * 60L * 24L * 30L)));
        item.setItemType(itemType);
        item.setManufacture(manufacture);
        item.setSerialNumber("11111111111");
        item.setWarrantyPeriod(wp);

        return item;
    }
}
