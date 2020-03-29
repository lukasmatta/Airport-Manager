package cz.fi.muni.pa165;
import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.dao.DaoSteward;
import cz.muni.fi.pa165.airportmanager.dao.FlightDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
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
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class FlightDaoTest {
    @Autowired
    private FlightDao flightDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void create() {
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);
        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,16,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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

    public void delete(){

        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);
        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,16,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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

    public void deleteById(){
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);
        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,16,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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

    public Flight update(){

        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);


        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,11,30);
        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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


        LocalDateTime departure_new = LocalDateTime.of(2020, Month.AUGUST, 25,14,30);
        LocalDateTime arrival_new = LocalDateTime.of(2020, Month.AUGUST, 25,19,30);

        flight.setArrival(arrival_new);
        flight.setDeparture(departure_new);

        pl.setName("Boeing");
        pl.setType(AirplaneType.First);
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

    public Flight findById(){
        Flight flight = new Flight();

        Airport origin = new Airport();
        origin.setCity("New York");
        origin.setCountry("USA");

        Airport destination = new Airport();
        destination.setCity("London");
        destination.setCountry("UK");

        flight.setOrigin(origin);
        flight.setDestination(destination);

        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);
        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,16,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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

    public List<Flight> findAll(){
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

        LocalDateTime arrival = LocalDateTime.of(2020, Month.AUGUST, 23,14,30);
        LocalDateTime departure = LocalDateTime.of(2020, Month.AUGUST, 23,16,30);

        flight.setArrival(arrival);
        flight.setDeparture(departure);

        Airplane airplane = new Airplane();

        Airplane pl = new Airplane();
        pl.setName("Airbus");
        pl.setType(AirplaneType.Second);
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

        LocalDateTime arrival1 = LocalDateTime.of(2020, Month.AUGUST, 23,17,30);
        LocalDateTime departure1= LocalDateTime.of(2020, Month.AUGUST, 23,20,30);

        flight1.setArrival(arrival1);
        flight1.setDeparture(departure1);

        Airplane airplane1 = new Airplane();

        Airplane pl1 = new Airplane();
        pl1.setName("Boeing");
        pl1.setType(AirplaneType.Second);
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