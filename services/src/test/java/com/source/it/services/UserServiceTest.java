package com.source.it.services;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.manager.UserManager;
import com.source.it.jdbc.model.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

@ContextConfiguration(locations = "classpath:services-test-context.xml")
public class UserServiceTest extends AbstractTestNGSpringContextTests {
    @InjectMocks
    private UserService sut;

    @Mock
    private UserManager userManager;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        User mike = new User();
        mike.setLogin("mike");

        when(userManager.getUserByLogin("mike"))
                .thenReturn(mike);

        when(userManager.getUserByLogin(anyString()))
                .thenThrow(new GenericDaoException("User not found"));
    }

    //@Test
    public void testGetByLogin() {
        //Given
        String login = "mike";

        //When
        User user = sut.getUserByLogin(login);

        //Then
        assertNotNull(user);
        assertEquals(user.getLogin(), login);
    }

    @Test
    public void testGetByUnexistingLogin() {
        //Given
        String login = "unexist";

        //When
        User user = sut.getUserByLogin(login);

        //Then
        assertNull(user);
        verify(userManager, times(1))
                .getUserByLogin(anyString());
        Mockito.verifyNoMoreInteractions(userManager);
    }
}
