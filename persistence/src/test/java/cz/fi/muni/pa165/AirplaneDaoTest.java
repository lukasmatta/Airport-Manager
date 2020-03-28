package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.dao.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ContextConfiguration(classes= PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AirplaneDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AirplaneDao airplaneDao;

//    @Autowired
//    private FlightDao flightDao;

    @Test
    public void findById() {

    }

    @Test
    public void findAll() {
        Airplane airplane1 = new Airplane();
        Airplane airplane2 = new Airplane();
        airplane1.setName("airplane1");
        airplane1.setType(AirplaneType.First);
        airplane2.setName("airplane2");
        airplane2.setType(AirplaneType.First);

        airplaneDao.insertAirplane(airplane1);
        airplaneDao.insertAirplane(airplane2);

        List<Airplane> airplanes = (List<Airplane>) airplaneDao.findAll();

        Assert.assertEquals(airplanes.size(), 2);

        Airplane airplane1assert = new Airplane();
        Airplane airplane2assert = new Airplane();
        airplane1assert.setName("airplane1");
        airplane2assert.setName("airplane2");

        Assert.assertTrue(airplanes.contains(airplane1assert));
        Assert.assertTrue(airplanes.contains(airplane2assert));
    }
}
