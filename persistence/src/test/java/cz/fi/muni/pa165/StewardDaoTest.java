package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

/**
 * Tests for Steward class
 *
 * @author Tomáš Janíček
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class StewardDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StewardDao stewardDao;


    @Test
    public void insertSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Peter");
        steward.setLastName("Kantek");
        Long stewardId = stewardDao.insertSteward(steward);
        assertNotNull(stewardDao.findById(stewardId));
        assertEquals(stewardDao.findById(stewardId), steward);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void insertNullAirport() {
        stewardDao.insertSteward(null);
    }

    @Test
    void updateSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Peter");
        steward.setLastName("Kantek");
        Long stewardId = stewardDao.insertSteward(steward);
        assertEquals(stewardDao.findById(stewardId), steward);
        steward.setFirstName("Peter");
        steward.setLastName("Caniga");
        assertEquals(stewardDao.findById(stewardId).getFirstName(), "Peter");
        assertEquals(stewardDao.findById(stewardId).getLastName(), "Caniga");
    }

    @Test
    void deleteSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Peter");
        steward.setLastName("Kantek");
        Long airportId = stewardDao.insertSteward(steward);
        assertEquals(stewardDao.findById(airportId), steward);
        stewardDao.deleteSteward(steward);
        assertNull(stewardDao.findById(airportId));
    }

    @Test
    void findById() {
        Steward steward = new Steward();
        steward.setFirstName("Peter");
        steward.setLastName("Kantek");
        Long stewardId = stewardDao.insertSteward(steward);
        assertEquals(stewardDao.findById(stewardId), steward);
        assertEquals(stewardDao.findById(stewardId).getFirstName(), "Peter");
        assertEquals(stewardDao.findById(stewardId).getLastName(), "Kantek");
        assertEquals(stewardDao.findById(stewardId).getId(), stewardId);
    }

    @Test
    void findAll() {
        assertEquals(stewardDao.findAll().size(), 0);
        Steward steward1 = new Steward();
        steward1.setFirstName("Peter");
        steward1.setLastName("Kantek");
        Steward steward2 = new Steward();
        steward2.setFirstName("Jozko");
        steward2.setLastName("Mrkvicka");
        stewardDao.insertSteward(steward1);
        stewardDao.insertSteward(steward2);
        assertEquals(stewardDao.findAll().size(), 2);
        stewardDao.deleteSteward(steward1);
        assertEquals(stewardDao.findAll().size(), 1);
        stewardDao.deleteSteward(steward2);
        assertEquals(stewardDao.findAll().size(), 0);
    }

}
