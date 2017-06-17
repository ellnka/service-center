package com.source.it.jdbc.dao;

import com.source.it.jdbc.GenericDaoTestUtils;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:services-jdbc-test-context.xml")
public class GenericDaoImplItemTest extends AbstractTestNGSpringContextTests {
    @Autowired
    @Qualifier("itemDao")
    private GenericDao<Item, Long> sut;

    @Autowired
    @Qualifier("warrantyPeriodDao")
    private GenericDao<WarrantyPeriod, Long> warrantyPeriodDao;

    @Autowired
    @Qualifier("itemTypeDao")
    private GenericDao<ItemType, Long> itemTypeDao;

    @Autowired
    @Qualifier("manufactureDao")
    private GenericDao<Manufacture, Long> manufactureDao;


    private Item doc;

    @BeforeClass
    public void setUp() throws Exception {
        doc = GenericDaoTestUtils.createItem(itemTypeDao, manufactureDao, warrantyPeriodDao);
    }

    @AfterClass
    public void tearDown() throws Exception {
        sut.delete(doc);
        itemTypeDao.delete(doc.getItemType());
        manufactureDao.delete(doc.getManufacture());
        warrantyPeriodDao.delete(doc.getWarrantyPeriod());
    }

    @Test(priority = 10)
    public void testCreateItem() throws Exception {
        //When
        long id = sut.create(doc);

        //Then
        assertEquals(1L, id);
    }

    @Test(priority = 20)
    public void testReadExistingItem() {
        //Given

        //When
        Item read = sut.read(1L);

        //Then
        assertEquals(read, doc, "Items should be equals");
    }

    @Test(priority = 30)
    public void testUpdateItem() {
        //Given
        doc.setSerialNumber("22222222");
        doc.setDateOfSale(new Date(System.currentTimeMillis()));

        //When
        sut.update(doc);

        //Then
        Item actual = sut.read(doc.getId());
        assertEquals(actual, doc);
    }

    @Test(priority = 40, expectedExceptions = {GenericDaoException.class},
            expectedExceptionsMessageRegExp = "Error reading item from data base .*")
    public void testDeleteItem() {
        //Given
        long id = doc.getId();
        try {
            assertNotNull(sut.read(id));
        } catch (GenericDaoException e) {
            assertTrue(false, "Item was not found in database - wrong test data!!");
        }

        //When
        sut.delete(doc);

        //Then
        sut.read(id);
    }
}
