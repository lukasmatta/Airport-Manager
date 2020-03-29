package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.dao.AirportDao;
import cz.muni.fi.pa165.airportmanager.dao.DaoSteward;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.testng.AssertJUnit.*;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class DaoStewardTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private DaoSteward daoSteward;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void insertSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Tony");
        steward.setLastName("Stark");
        Long stewardId = daoSteward.insertSteward(steward);
        assertNotNull(daoSteward.findById(stewardId));
        assertEquals(daoSteward.findById(stewardId), steward);
    }

    @Test
    void updateSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Tony");
        steward.setLastName("Stark");
        Long stewardId = daoSteward.insertSteward(steward);
        assertEquals(daoSteward.findById(stewardId), steward);
        steward.setFirstName("Luffy");
        steward.setLastName("Monkey D");
        assertEquals(daoSteward.findById(stewardId).getFirstName(), "Luffy");
        assertEquals(daoSteward.findById(stewardId).getLastName(), "Monkey D");
    }

    @Test
    void deleteSteward() {
        Steward steward = new Steward();
        steward.setFirstName("Tony");
        steward.setLastName("Stark");
        Long stewardId = daoSteward.insertSteward(steward);
        assertEquals(daoSteward.findById(stewardId), steward);
        daoSteward.deleteSteward(steward);
        assertNull(daoSteward.findById(stewardId));
    }

    @Test
    void findById() {
        Steward steward = new Steward();
        steward.setFirstName("Tony");
        steward.setLastName("Stark");
        Long stewardId = daoSteward.insertSteward(steward);
        assertEquals(daoSteward.findById(stewardId), steward);
        assertEquals(daoSteward.findById(stewardId).getFirstName(), "Tony");
        assertEquals(daoSteward.findById(stewardId).getLastName(), "Stark");
        assertEquals(daoSteward.findById(stewardId).getId(), stewardId);
    }

    @Test
    void findAll() {
        assertEquals(daoSteward.findAll().size(), 0);
        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        daoSteward.insertSteward(steward1);
        daoSteward.insertSteward(steward2);
        assertEquals(daoSteward.findAll().size(), 2);
        daoSteward.deleteSteward(steward1);
        assertEquals(daoSteward.findAll().size(), 1);
        daoSteward.deleteSteward(steward2);
        assertEquals(daoSteward.findAll().size(), 0);
    }
}