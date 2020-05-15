package cz.fi.muni.pa165;


import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.dao.UserDao;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

/**
 * Tests for User class
 *
 * @author Tomáš Janíček
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private UserDao userDAO;

    private User admin, user;

    @BeforeMethod
    public void init(){
        admin = new User();
        admin.setName("admin");
        admin.setPasswordHash("admin");
        admin.setAdmin(true);

        user = new User();
        user.setName("user");
        user.setPasswordHash("user");
        user.setAdmin(false);
    }

    @Test
    public void insertUser() {
        Long userId = userDAO.insertUser(user);
        assertNotNull(userDAO.findById(userId));
        assertEquals(userDAO.findById(userId), user);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void insertNullUser() {
        userDAO.insertUser(null);
    }

    @Test
    void updateUser() {
        Long adminId = userDAO.insertUser(admin);
        assertEquals(userDAO.findById(adminId), admin);
        admin.setName("another_user");
        admin.setAdmin(false);
        assertEquals(userDAO.findById(adminId).getName(), "another_user");
        assertFalse(userDAO.findById(adminId).isAdmin());
    }

    @Test
    void deleteUser() {
        Long userId = userDAO.insertUser(user);
        assertEquals(userDAO.findById(userId), user);
        userDAO.deleteUser(user);
        assertNull(userDAO.findById(userId));
    }

    @Test
    void findById() {
        Long userId = userDAO.insertUser(user);
        assertEquals(userDAO.findById(userId), user);
        assertEquals(userDAO.findById(userId).getName(), "user");
        assertFalse(userDAO.findById(userId).isAdmin());
        assertEquals(userDAO.findById(userId).getId(), userId);
    }

    @Test
    void findAll() {
        assertEquals(userDAO.findAll().size(), 0);
        userDAO.insertUser(user);
        userDAO.insertUser(admin);
        assertEquals(userDAO.findAll().size(), 2);
        userDAO.deleteUser(user);
        assertEquals(userDAO.findAll().size(), 1);
        userDAO.deleteUser(admin);
        assertEquals(userDAO.findAll().size(), 0);
    }

}
