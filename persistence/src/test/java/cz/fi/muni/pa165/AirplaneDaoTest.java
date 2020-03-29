package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.dao.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
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

import static org.testng.AssertJUnit.*;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AirplaneDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AirplaneDao airplaneDao;

    @BeforeMethod
    private void makePlanes() {
        for (int i = 0; i < 15; ++i) {
            Airplane pl = new Airplane();
            pl.setCapacity(15 * i);
            pl.setName("plane" + i);
            pl.setType((AirplaneType.values()[i % 2]));
            airplaneDao.insertAirplane(pl);
        }
    }

    @Test
    void findById() {
        Airplane pl = new Airplane();
        pl.setName("Boeing");
        pl.setType(AirplaneType.First);
        pl.setCapacity(300);
        Long plId = airplaneDao.insertAirplane(pl);
        assertEquals(airplaneDao.findById(plId).getId(), plId);
        assertEquals(airplaneDao.findById(plId).getCapacity(), 300);
        assertEquals(airplaneDao.findById(plId).getName(), "Boeing");
        assertNull(airplaneDao.findById(Long.MAX_VALUE));
        assertNull(airplaneDao.findById(-1L));
    }

    @Test
    void findAll() {
        assertEquals(airplaneDao.findAll().size(), 15);
        Airplane pl = new Airplane();
        pl.setName("Boeing");
        pl.setType(AirplaneType.First);
        pl.setCapacity(200);
        airplaneDao.insertAirplane(pl);
        assertEquals(airplaneDao.findAll().size(), 16);
    }

    @Test
    void insertAirplane() {
        airplaneDao.insertAirplane(new Airplane());
        assertEquals(airplaneDao.findAll().size(), 16);
        Airplane pl = new Airplane();
        pl.setType(AirplaneType.Second);
        pl.setCapacity(300);
        pl.setName("Airbus");
        Long plId = airplaneDao.insertAirplane(pl);
        assertNotNull( airplaneDao.findById(plId).getType());
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    void insertNullAirplane() {
        airplaneDao.insertAirplane(null);
    }

    @Test
    void updateAirplane() {
        Airplane pl = new Airplane();
        pl.setName("Boeing");
        pl.setType(AirplaneType.Second);
        pl.setCapacity(500);
        Long plId = airplaneDao.insertAirplane(pl);
        pl.setName("Airbus");
        pl.setType(AirplaneType.First);
        pl.setCapacity(400);
        airplaneDao.updateAirplane(pl);
        assertEquals(airplaneDao.findById(plId).getName(), "Airbus");
        assertEquals(airplaneDao.findById(plId).getType(), AirplaneType.First);
        assertEquals(airplaneDao.findById(plId).getCapacity(), 400);
    }

    @Test
    void deleteAirplane() {
        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
        pl.setCapacity(100);
        Long plId = airplaneDao.insertAirplane(pl);
        assertNotNull(airplaneDao.findById(plId));
        airplaneDao.deleteAirplane(pl);
        assertNull(airplaneDao.findById(plId));
        assertEquals(airplaneDao.findAll().size(), 15);
    }

}
