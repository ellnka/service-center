package com.source.it.jdbc.dao;

import com.source.it.jdbc.GenericDaoTestUtils;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import java.sql.SQLException;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


@ContextConfiguration(locations = "classpath:services-jdbc-test-context.xml")
public class GenericDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    @Qualifier("userDao")
    private GenericDao<User, Long> sut;

    @Autowired
    private GenericDao<UserRole, Long> userRoleDao;

    private User doc;

    @BeforeClass
    public void setUp() throws SQLException {
        doc = GenericDaoTestUtils.createUser();
        UserRole userRole = new UserRole();
        userRole.setRole("Admin");

        userRoleDao.create(userRole);
        doc.setUserRole(userRole);
    }

    @AfterClass
    public void tearDown() throws Exception {
        userRoleDao.delete(doc.getUserRole());
    }


    @Test (priority = 1, expectedExceptions = GenericDaoException.class,
    expectedExceptionsMessageRegExp = "Error reading user from data base .*")
    public void testReadUnexistingUser() {
        //Given
        long unexistingUserId = 1L;

        //When
        sut.read(unexistingUserId);
    }

    @Test(priority = 20)
    public void testWriteNewUser() {
        //Given


        //When
        long id = sut.create(doc);

        //Then
        assertEquals(1L, id, "First user written to DB should have id == 1 but was " + id);
    }

    @Test(priority = 30)
    public void testReadExistingUser() {
        //Given

        //When
        User user = sut.read(1L);

        //Then
        assertEquals(user, doc, "Users should be equals");
    }

    @Test(priority = 40)
    public void testUpdateUser() {
        //Given
        doc.setLogin("john");
        doc.setName("John");

        //When
        sut.update(doc);

        //Then
        User actual = sut.read(doc.getId());
        assertEquals(actual, doc);
    }

    @Test(priority = 50, expectedExceptions = {GenericDaoException.class},
            expectedExceptionsMessageRegExp = "Error reading user from data base .*")
    public void testDeleteUser() {
        //Given
        long id = doc.getId();
        try {
            assertNotNull(sut.read(id));
        } catch (GenericDaoException e) {
            assertTrue(false, "User was not found in database - wrong test data!!");
        }

        //When
        sut.delete(doc);

        //Then
        sut.read(id);
    }
}
