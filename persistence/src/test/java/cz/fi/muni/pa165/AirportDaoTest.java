package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.dao.AirportDao;
import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
public class AirportDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AirportDao airportDAO;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void insertAirport() {
        Airport airport = new Airport();
        airport.setCity("New York");
        airport.setCountry("USA");
        Long airportId = airportDAO.insertAirport(airport);
        assertNotNull(airportDAO.findById(airportId));
        assertEquals(airportDAO.findById(airportId), airport);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void insertNullAirport() {
        airportDAO.insertAirport(null);
    }

    @Test
    void updateAirport() {
        Airport airport = new Airport();
        airport.setCountry("Canada");
        airport.setCity("Vancouver");
        Long airportId = airportDAO.insertAirport(airport);
        assertEquals(airportDAO.findById(airportId), airport);
        airport.setCountry("Mexico");
        airport.setCity("Mexico City");
        assertEquals(airportDAO.findById(airportId).getCity(), "Mexico City");
        assertEquals(airportDAO.findById(airportId).getCountry(), "Mexico");
    }

    @Test
    void deleteAirport() {
        Airport airport = new Airport();
        airport.setCountry("UK");
        airport.setCity("London");
        Long airportId = airportDAO.insertAirport(airport);
        assertEquals(airportDAO.findById(airportId), airport);
        airportDAO.deleteAirport(airport);
        assertNull(airportDAO.findById(airportId));
    }

    @Test
    void findById() {
        Airport airport = new Airport();
        airport.setCountry("Germany");
        airport.setCity("Frankfurt");
        Long airportId = airportDAO.insertAirport(airport);
        assertEquals(airportDAO.findById(airportId), airport);
        assertEquals(airportDAO.findById(airportId).getCity(), "Frankfurt");
        assertEquals(airportDAO.findById(airportId).getCountry(), "Germany");
        assertEquals(airportDAO.findById(airportId).getId(), airportId);
    }

    @Test
    void findAll() {
        assertEquals(airportDAO.findAll().size(), 0);
        Airport airport1 = new Airport();
        airport1.setCity("LA");
        airport1.setCountry("USA");
        Airport airport2 = new Airport();
        airport1.setCity("San Francisco");
        airport1.setCountry("USA");
        airportDAO.insertAirport(airport1);
        airportDAO.insertAirport(airport2);
        assertEquals(airportDAO.findAll().size(), 2);
        airportDAO.deleteAirport(airport1);
        assertEquals(airportDAO.findAll().size(), 1);
        airportDAO.deleteAirport(airport2);
        assertEquals(airportDAO.findAll().size(), 0);
    }

}
