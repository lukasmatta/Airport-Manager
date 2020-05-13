package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dao.UserDao;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Mock testing of UserService
 *
 * @author Tomáš Janíček
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private User admin;

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(25L);
        user.setName("user");
        user.setPasswordHash("user");
        user.setAdmin(false);

        admin = new User();
        admin.setId(10L);
        admin.setName("admin");
        admin.setPasswordHash("admin");
        admin.setAdmin(true);
    }


    @Test
    public void testFindById() {
        when(userDao.findById(25L)).thenReturn(user);
        when(userDao.findById(10L)).thenReturn(admin);

        User user = userService.findById(25L);
        User admin = userService.findById(10L);

        assertEquals(user.getId(), Long.valueOf(25L));
        assertEquals(user.getName(), "user");

        assertEquals(admin.getId(), Long.valueOf(10L));
        assertEquals(admin.getName(), "admin");

        verify(userDao, times(1)).findById(25L);
        verify(userDao, times(1)).findById(10L);
    }

    @Test
    public void testFindAll() {
        when(userDao.findAll()).thenReturn(new ArrayList<>(Arrays.asList(user, admin)));
        List<User> allUsers = userService.findAll();

        assertEquals(allUsers.get(0).getId(), Long.valueOf(25L));
        assertEquals(allUsers.get(1).getId(), Long.valueOf(10L));
        verify(userDao, times(1)).findAll();
    }
}
