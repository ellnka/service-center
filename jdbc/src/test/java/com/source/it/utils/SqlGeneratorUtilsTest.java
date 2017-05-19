package com.source.it.utils;

import com.source.it.jdbc.model.*;
import com.source.it.jdbc.utils.SqlGeneratorUtils;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.assertEquals;

public class SqlGeneratorUtilsTest {

    @Test
    public void testGenerateSqlForItemType() {
        //Given
        ItemType itemType = new ItemType();
        itemType.setName("Test");

        //When
        String sql = SqlGeneratorUtils.generateSelectSql(itemType);

        //Then
        assertEquals(sql, "SELECT IT.ID, IT.NAME FROM ITEM_TYPES IT WHERE IT.ID=?");

    }

    @Test
    public void testGenerateSqlForWarrantyPeriod() {
        //Given
        WarrantyPeriod wp = new WarrantyPeriod();
        wp.setDays(365);
        wp.setName("1 year");

        //When
        String sql = SqlGeneratorUtils.generateSelectSql(wp);

        //Then
        assertEquals(sql, "SELECT WP.ID, WP.DAYS, WP.NAME FROM WARRANTY_PERIODS WP WHERE WP.ID=?");
    }

    @Test
    public void testGenerateSqlForUser() {
        //Given
        User user = new User();
        user.setEmail("123@c.com");
        user.setLastName("Smith");
        user.setLogin("John");
        user.setName("John");
        user.setPassword("123456");
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRole("SomeRole");
        user.setUserRole(userRole);

        //When
        String userRoleSql = SqlGeneratorUtils.generateSelectSql(userRole);
        String userSql = SqlGeneratorUtils.generateSelectSql(user);

        //Then

        assertEquals(userSql, "SELECT U.ID, U.NAME, U.LASTNAME, U.LOGIN, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, UR.ROLE FROM USERS U JOIN USER_ROLES UR ON U.USER_ROLE_ID=UR.ID WHERE U.ID=?");
        assertEquals(userRoleSql, "SELECT UR.ID, UR.ROLE FROM USER_ROLES UR WHERE UR.ID=?");
    }

    @Test
    public void testGenerateSqlForItem() {
        //Given
        Item item = new Item();
        item.setDateOfSale(new Date(System.currentTimeMillis()));
        item.setSerialNumber("123");

        WarrantyPeriod wp = new WarrantyPeriod();
        wp.setDays(365);
        wp.setName("1 year");

        Manufacture manufacture = new Manufacture();
        manufacture.setName("Philips");
        manufacture.setId(1L);

        ItemType itemType = new ItemType();
        itemType.setId(1L);
        itemType.setName("Cell Phone");

        item.setWarrantyPeriod(wp);
        item.setManufacture(manufacture);
        item.setItemType(itemType);

        //When
        String sql = SqlGeneratorUtils.generateSelectSql(item);

        //Then
        System.out.println(sql);
    }
}
