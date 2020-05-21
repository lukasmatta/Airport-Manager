package cz.fi.muni.pa165;
import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.dao.FlightDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import cz.muni.fi.pa165.airportmanager.exceptions.OverlappingTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * Tests for Flight class
 *
 * @author  Almas Shakirtkhanov
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class FlightDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private FlightDao flightDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void create() throws OverlappingTimeException {
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);

        Long flightId = flightDao.create(flight);
        assertNotNull(flightDao.findById(flightId));
        assertEquals(flightDao.findById(flightId), flight);
    }

    @Test
    public void delete() throws OverlappingTimeException {

        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);

        Long flightId = flightDao.create(flight);
        assertEquals(flightDao.findById(flightId), flight);
        flightDao.delete(flight);
        assertNull(flightDao.findById(flightId));

    }

    @Test
    public void deleteById() throws OverlappingTimeException {
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);

        Long flightId = flightDao.create(flight);
        assertEquals(flightDao.findById(flightId), flight);
        flightDao.deleteById(flightId);
        assertNull(flightDao.findById(flightId));
    }

    @Test(expectedExceptions = OverlappingTimeException.class)
    public void testCorrectStewardAndPlane() throws OverlappingTimeException {
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");
        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);
        flight.setPlane(pl);

        Flight flight2 = new Flight();
        ZonedDateTime arrival2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,13,30), ZoneOffset.UTC);
        ZonedDateTime departure2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,17,30), ZoneOffset.UTC);
        flight2.setArrival(arrival2);
        flight2.setDeparture(departure2);
        flight2.setOrigin(origin);
        flight2.setDestination(destination);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");

        steward1.addFlight(flight);
        flight2.addSteward(steward1);


//        Steward steward2 = new Steward();
//        steward2.setFirstName("Leo");
//        steward2.setLastName("Messi");
//
//        flight.addSteward(steward1);
//        flight.addSteward(steward2);
    }

    @Test
    public Flight update() throws OverlappingTimeException {

        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);


        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);

        Long flightId = flightDao.create(flight);
        assertEquals(flightDao.findById(flightId), flight);


        origin.setCity("Astana");
        origin.setCountry("Kazakhstan");

        destination.setCity("Almaty");
        destination.setCountry("Kazakhstan");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime departure_new = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 25,14,30), ZoneOffset.UTC);
        ZonedDateTime arrival_new = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 25,19,30), ZoneOffset.UTC);

        flight.setArrival(arrival_new);
        flight.setDeparture(departure_new);

        pl.setName("Boeing");
        pl.setType(AirplaneType.PRIVATE);
        pl.setCapacity(70);

        flight.setPlane(pl);

        steward1.setFirstName("Roronoa");
        steward1.setLastName("Zoro");

        steward2.setFirstName("Cris");
        steward2.setLastName("Ron");

        flight.addSteward(steward1);
        flight.addSteward(steward2);


        assertEquals(flightDao.findById(flightId).getOrigin().getCity(), "Astana");
        assertEquals(flightDao.findById(flightId).getDestination().getCity(), "Almaty");
        assertEquals(flightDao.findById(flightId).getArrival(), LocalDateTime.of(2020, Month.AUGUST, 25,19,30));
        assertEquals(flightDao.findById(flightId).getDeparture(), LocalDateTime.of(2020, Month.AUGUST, 25,14,30));
        assertEquals(flightDao.findById(flightId).getPlane().getName(), "Boeing");
        assertEquals(flightDao.findById(flightId).getStewards().size(), 2);

        return flight;

    }

    @Test
    public Flight findById() throws OverlappingTimeException {
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);

        Long flightId = flightDao.create(flight);
        assertNotNull(flightDao.findById(flightId));
        assertEquals(flightDao.findById(flightId).getId(), flightId);

        return flight;
    }

    @Test
    public List<Flight> findAll() throws OverlappingTimeException {
        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        ZonedDateTime departure = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,14,30), ZoneOffset.UTC);
        ZonedDateTime arrival = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,16,30), ZoneOffset.UTC);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.COMMERCIAL);
        pl.setCapacity(100);

        flight.setPlane(pl);

        Steward steward1 = new Steward();
        steward1.setFirstName("Tony");
        steward1.setLastName("Stark");
        Steward steward2 = new Steward();
        steward2.setFirstName("Leo");
        steward2.setLastName("Messi");

        flight.addSteward(steward1);
        flight.addSteward(steward2);


        Flight flight1 = new Flight();

        Airport origin1 = new Airport();
        origin1.setCity("Astana");
        origin1.setCountry("Kazakhstan");

        Airport destination1= new Airport();
        destination1.setCity("Almaty");
        destination1.setCountry("Kazakhstan");

        flight1.setOrigin(origin1);
        flight1.setDestination(destination1);

        ZonedDateTime departure1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,17,00), ZoneOffset.UTC);
        ZonedDateTime arrival1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 23,20,30), ZoneOffset.UTC);

        flight1.setArrival(arrival1);
        flight1.setDeparture(departure1);

        Airplane airplane1 = new Airplane();

        Airplane pl1 = new Airplane();
        pl1.setName("Boeing");
        pl1.setType(AirplaneType.COMMERCIAL);
        pl1.setCapacity(70);

        flight1.setPlane(pl1);

        Steward steward1_new = new Steward();
        steward1_new.setFirstName("Cris");
        steward1_new.setLastName("James");
        Steward steward2_new = new Steward();
        steward2_new.setFirstName("Tima");
        steward2_new.setLastName("Beloruskih");

        flight1.addSteward(steward1_new);
        flight1.addSteward(steward2_new);


        flights.add(flight);
        flights.add(flight1);
        return flights;
    }
}