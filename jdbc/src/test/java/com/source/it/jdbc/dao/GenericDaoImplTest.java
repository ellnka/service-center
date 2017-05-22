package com.source.it.jdbc.dao;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.model.UserRole;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;


public class GenericDaoImplTest {
    private GenericDao<User, Long> sut = AbstractDaoFactory.getDaoFactory(User.class).getDao();


    @BeforeClass
    public void setUp() {
        System.out.println("Before Tests");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("After Tests");
    }

    @BeforeMethod
    public void before() {
        System.out.println("before test case");
    }

    @AfterMethod
    public void after() {
        System.out.println("after test case");
    }

    @Test
    public void testReadExistingUser() {
        //Given
        User expected  = new User();
        expected.setId(1L);
        expected.setEmail("mike@mysite.com");
        expected.setLastName("Smith");
        expected.setLogin("mike");
        expected.setName("Mike");
        expected.setPassword("123");
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRole("Admin");
        expected.setUserRole(userRole);

        //When
        User user = sut.read(1L);

        //Then
        assertEquals(user, expected, "Users should be equals");
    }

    @Test (priority = 1, expectedExceptions = GenericDaoException.class,
    expectedExceptionsMessageRegExp = "Error reading user from data base .*")
    public void testReadUnexistingUser() {
        //Given
        long unexistingUserId = 5L;

        //When
        sut.read(unexistingUserId);

    }
}
