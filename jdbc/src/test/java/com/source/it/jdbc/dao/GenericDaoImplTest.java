package com.source.it.jdbc.dao;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.model.UserRole;
import org.h2.jdbcx.JdbcDataSource;
import org.testng.annotations.*;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class GenericDaoImplTest {
    private GenericDao<User, Long> sut = AbstractDaoFactory.getDaoFactory(User.class).getDao();
    private GenericDao<UserRole, Long> userRoleDao = AbstractDaoFactory.getDaoFactory(UserRole.class).getDao();
    private User doc;

    @BeforeClass
    public void setUp() throws SQLException {
        H2Starter.startH2();

        GenericDaoImpl dao = ((GenericDaoImpl)sut);

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL");
        dataSource.setUser("sa");
        dao.dataSource = dataSource;

        GenericDaoImpl userRoleDaoForTest = (GenericDaoImpl) userRoleDao;
        userRoleDaoForTest.dataSource = dataSource;

        doc = new User();
        doc.setId(1L);
        doc.setEmail("mike@mysite.com");
        doc.setLastName("Smith");
        doc.setLogin("mike");
        doc.setName("Mike");
        doc.setPassword("123");
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRole("Admin");

        userRoleDao.create(userRole);
        doc.setUserRole(userRole);

    }

    @AfterClass
    public void tearDown() {
        H2Starter.shutDownH2();
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
