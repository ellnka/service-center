package com.source.it.jdbc.dao;

import com.source.it.jdbc.GenericDaoTestUtils;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:services-jdbc-test-context.xml")
public class GenericDaoImplOrderTest extends AbstractTestNGSpringContextTests {
    @Autowired
    @Qualifier("orderDao")
    private GenericDao<Order, Long> sut;

    @Autowired
    private GenericDao<UserRole, Long> userRoleDao;

    @Autowired
    @Qualifier("userDao")
    private GenericDao<User, Long> userDao;

    @Autowired
    @Qualifier("warrantyPeriodDao")
    private GenericDao<WarrantyPeriod, Long> warrantyPeriodDao;

    @Autowired
    @Qualifier("itemTypeDao")
    private GenericDao<ItemType, Long> itemTypeDao;

    @Autowired
    @Qualifier("manufactureDao")
    private GenericDao<Manufacture, Long> manufactureDao;

    @Autowired
    @Qualifier("itemDao")
    private GenericDao<Item, Long> itemDao;

    private Order doc;

    private User user;

    @BeforeClass
    public void setUp() {
        Item item = GenericDaoTestUtils.createItem(itemTypeDao, manufactureDao, warrantyPeriodDao);
        itemDao.create(item);
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRole("Admin");
        userRoleDao.create(userRole);
        user = GenericDaoTestUtils.createUser();
        user.setUserRole(userRole);
        userDao.create(user);
        doc = new Order();
        doc.setStatus(Status.NEW);
        doc.setWarranty(true);
        doc.setDate(new Date(System.currentTimeMillis()));
        doc.setUser(user);
        doc.setItem(item);
    }

    @AfterClass
    public void tearDown() throws Exception {
        sut.delete(doc);
    }

    @Test(priority = 10)
    public void testCreateOrder() {
        //Given


        //When
        long id = sut.create(doc);

        //Then
        assertEquals(1L, id);
    }

    @Test(priority = 30)
    public void testUpdateOrder() {
        //Given
        doc.setDate(new Date(System.currentTimeMillis()));
        doc.setStatus(Status.IN_PROCESS);
        doc.setWarranty(false);

        //When
        sut.update(doc);

        //Then
        Order actual = sut.read(doc.getId());
        assertEquals(actual, doc);
    }

    @Test(priority = 40, expectedExceptions = {GenericDaoException.class},
            expectedExceptionsMessageRegExp = "Error reading order from data base .*")
    public void testDeleteItem() {
        //Given
        long id = doc.getId();
        try {
            assertNotNull(sut.read(id));
        } catch (GenericDaoException e) {
            assertTrue(false, "Order was not found in database - wrong test data!!");
        }

        //When
        sut.delete(doc);

        //Then
        sut.read(id);
    }

}
