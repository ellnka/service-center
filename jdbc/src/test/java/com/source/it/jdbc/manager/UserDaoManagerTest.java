package com.source.it.jdbc.manager;

import com.source.it.jdbc.GenericDaoTestUtils;
import com.source.it.jdbc.dao.GenericDao;
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

@ContextConfiguration(locations = "classpath:services-jdbc-test-context.xml")
public class UserDaoManagerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    @Qualifier("userManager")
    private UserManager sut;

    @Autowired
    private GenericDao<UserRole, Long> userRoleDao;

    //private GenericDao<UserRole, Long> userRoleDao = AbstractDaoFactory.getDaoFactory(UserRole.class).getDao();
    @Autowired
    private GenericDao<User, Long> userDao;

    private User doc;

    @BeforeClass
    public void setUp() throws SQLException {
        doc = GenericDaoTestUtils.createUser();
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setRole("Admin");
        userRoleDao.create(userRole);
        doc.setUserRole(userRole);
    }

    @AfterClass
    public void tearDown() throws Exception {
        userDao.delete(doc);
        userRoleDao.delete(doc.getUserRole());
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
