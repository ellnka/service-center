package com.source.it.jdbc.manager;

import com.source.it.jdbc.dao.AbstractDaoFactory;
import com.source.it.jdbc.dao.GenericDao;
import com.source.it.jdbc.dao.GenericDaoImpl;
import com.source.it.jdbc.dao.H2Starter;
import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.User;
import com.source.it.jdbc.model.UserRole;
import org.h2.jdbcx.JdbcDataSource;
import org.testng.annotations.*;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class UsermanagerTest {
    private UserManager sut = AbstractManagerFactory.getManagerFactory().getUserManager();
    private GenericDao<UserRole, Long> userRoleDao = AbstractDaoFactory.getDaoFactory(UserRole.class).getDao();
    private GenericDao<User, Long> userDao = AbstractDaoFactory.getDaoFactory(User.class).getDao();

    private User doc;

    @BeforeClass
    public void setUp() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL");
        dataSource.setUser("sa");
        sut.dataSource = dataSource;

        ((GenericDaoImpl)userDao).setDataSource(dataSource);

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
        doc.setUserRole(userRole);

    }

    @AfterSuite
    public void tearDown() {
        H2Starter.shutDownH2();
    }

    @Test(priority = 1)
    public void testGetUserByLogin() {
        //Given
        userDao.create(doc);

        //When
        User actual = sut.getUserByLogin(doc.getLogin());

        //Then
        assertEquals(actual, doc);
    }

    @Test(priority = 10, /*Then*/ expectedExceptions = {GenericDaoException.class})
    public void testGetUserByLoginForUnexistingUser() {
        //Given
        String unexistingLogin = "Some unexisting login";

        //When
        User actual = sut.getUserByLogin(unexistingLogin);
    }
}
