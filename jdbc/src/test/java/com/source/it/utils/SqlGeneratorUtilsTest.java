package com.source.it.utils;

import com.source.it.jdbc.model.*;
import org.testng.annotations.Test;

import java.sql.Date;

import static com.source.it.jdbc.utils.SqlGeneratorUtils.*;
import static org.testng.Assert.assertEquals;

public class SqlGeneratorUtilsTest {

    @Test
    public void testGenerateSelectSqlForItemType() {
        //Given
        ItemType itemType = new ItemType();
        itemType.setItemTypeName("Test");

        //When
        String sql = generateSelectSql(itemType);

        //Then
        assertEquals(sql, "SELECT IT.ID, IT.ITEMTYPENAME FROM ITEM_TYPES IT WHERE IT.ID=?");

    }

    @Test
    public void testGenerateSelectSqlForWarrantyPeriod() {
        //Given
        WarrantyPeriod wp = new WarrantyPeriod();
        wp.setDays(365);
        wp.setWpName("1 year");

        //When
        String sql = generateSelectSql(wp);

        //Then
        assertEquals(sql, "SELECT WP.ID, WP.DAYS, WP.WPNAME FROM WARRANTY_PERIODS WP WHERE WP.ID=?");
    }

    @Test
    public void testGenerateSelectSqlForUser() {
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
        String userRoleSql = generateSelectSql(userRole);
        String userSql = generateSelectSql(user);

        //Then

        assertEquals(userSql, "SELECT U.ID, U.NAME, U.LASTNAME, U.LOGIN, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, UR.ROLE FROM USERS U JOIN USER_ROLES UR ON U.USER_ROLE_ID=UR.ID WHERE U.ID=?");
        assertEquals(userRoleSql, "SELECT UR.ID, UR.ROLE FROM USER_ROLES UR WHERE UR.ID=?");
    }

    @Test
    public void testGenerateSelectSqlForItem() {
        //Given
        Item item = new Item();
        item.setDateOfSale(new Date(System.currentTimeMillis()));
        item.setSerialNumber("123");

        WarrantyPeriod wp = new WarrantyPeriod();
        wp.setDays(365);
        wp.setWpName("1 year");

        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureName("Philips");
        manufacture.setId(1L);

        ItemType itemType = new ItemType();
        itemType.setId(1L);
        itemType.setItemTypeName("Cell Phone");

        item.setWarrantyPeriod(wp);
        item.setManufacture(manufacture);
        item.setItemType(itemType);

        //When
        String sql = generateSelectSql(item);

        //Then
        assertEquals(sql, "SELECT I.ID, I.MANUFACTURE_ID, I.ITEM_TYPE_ID, I.WARRANTY_PERIOD_ID, I.DATEOFSALE, I.SERIALNUMBER, M.MANUFACTURENAME, IT.ITEMTYPENAME, WP.DAYS, WP.WPNAME FROM ITEMS I JOIN MANUFACTURES M ON I.MANUFACTURE_ID=M.ID JOIN ITEM_TYPES IT ON I.ITEM_TYPE_ID=IT.ID JOIN WARRANTY_PERIODS WP ON I.WARRANTY_PERIOD_ID=WP.ID WHERE I.ID=?");
    }

    @Test
    public void testGenerateCreateSqlForUser() {
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
        String userRoleSql = generateCreateSql(userRole);
        String userSql = generateCreateSql(user);

        //Then
        assertEquals(userSql, "INSERT INTO USERS (NAME, LASTNAME, LOGIN, PASSWORD, EMAIL, USER_ROLE_ID) VALUES (?, ?, ?, ?, ?, ?)");
        assertEquals(userRoleSql, "INSERT INTO USER_ROLES (ROLE) VALUES (?)");
    }

    @Test
    public void testGenerateDeleteSqlForUser() {
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
        String userRoleSql = generateDeleteSql(userRole);
        String userSql = generateDeleteSql(user);

        //Then

        assertEquals(userSql, "DELETE FROM USERS WHERE ID=?");
        assertEquals(userRoleSql, "DELETE FROM USER_ROLES WHERE ID=?");
    }

    @Test
    public void testGenerateUserUpdateSql() {
        //Given
        User user = new User();
        user.setId(1L);
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
        String userRoleSql = generateUpdateSql(userRole);
        String userSql = generateUpdateSql(user);

        //Then
        assertEquals(userSql, "UPDATE USERS SET NAME='John', LASTNAME='Smith', LOGIN='John', PASSWORD='123456', EMAIL='123@c.com', USER_ROLE_ID=1 WHERE ID=1");
        assertEquals(userRoleSql, "UPDATE USER_ROLES SET ROLE='SomeRole' WHERE ID=1");
    }

}
