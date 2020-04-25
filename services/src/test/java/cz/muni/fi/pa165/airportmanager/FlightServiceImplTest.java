package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dao.FlightDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Mock testing of basic CRUD Flight operations on the service layer
 *
 * @author Petr Kantek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class FlightServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private FlightDao flightDao;

    @Mock
    private Airport originAirport;

    @Mock
    private Airport destinationAirport;

    @Mock
    private Steward steward;

    @Mock
    private Airplane airplane;

    @InjectMocks
    private FlightServiceImpl flightService;

    private Flight flight1;
    private Flight flight2;

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        flight1 = new Flight();
        flight1.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 9, 1), ZoneOffset.UTC));
        flight1.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 14, 1), ZoneOffset.UTC));
        flight1.setId(42L);
        flight1.setStewards(new HashSet<>());
        flight1.setOrigin(originAirport);
        flight1.setDestination(destinationAirport);
        flight1.setPlane(airplane);

        flight2 = new Flight();
        flight2.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 14, 13, 1), ZoneOffset.UTC));
        flight2.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 15, 3, 30), ZoneOffset.UTC));
        flight2.setId(666L);
        flight2.setStewards(new HashSet<>(Collections.singletonList(steward)));
        flight2.setOrigin(originAirport);
        flight2.setDestination(destinationAirport);
        flight2.setPlane(airplane);
    }

    @Test
    public void testCreate() {

        when(flightDao.create(flight1)).thenReturn(42L);
        when(flightDao.create(flight2)).thenReturn(666L);

        Long flight1ID = flightService.create(flight1);
        Long flight2ID = flightService.create(flight2);

        assertEquals(flight1ID, flight1.getId());
        assertEquals(flight2ID, flight2.getId());

        verify(flightDao, times(1)).create(flight1);
        verify(flightDao, times(1)).create(flight2);
    }

    @Test
    public void testDelete() {
        flightService.delete(flight1);
        flightService.delete(flight2);

        verify(flightDao, times(1)).delete(flight1);
        verify(flightDao, times(1)).delete(flight2);
    }

    @Test
    public void testDeleteById() {
        flightService.deleteById(flight1.getId());
        flightService.deleteById(flight2.getId());

        verify(flightDao, times(1)).deleteById(42L);
        verify(flightDao, times(1)).deleteById(666L);
    }

    @Test
    public void testUpdate() {
        flightService.update(flight1);
        flightService.update(flight2);

        verify(flightDao, times(1)).update(flight1);
        verify(flightDao, times(1)).update(flight2);
    }

    @Test
    public void testFindById() {
        when(flightDao.findById(42L)).thenReturn(flight1);
        when(flightDao.findById(666L)).thenReturn(flight2);

        Flight flight1ID = flightService.findById(42L);
        Flight flight2ID = flightService.findById(666L);

        assertEquals(flight1ID.getArrival(), ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 9, 1), ZoneOffset.UTC));
        assertEquals(flight1ID.getDeparture(), ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 14, 1), ZoneOffset.UTC));
        assertEquals(flight1ID.getDestination(), destinationAirport);
        assertEquals(flight1ID.getOrigin(), originAirport);
        assertEquals(flight1ID.getId(), Long.valueOf(42L));
        assertEquals(flight1ID.getPlane(), airplane);
        assertEquals(flight1ID.getStewards().size(), 0);

        assertEquals(flight2ID.getArrival(), ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 14, 13, 1), ZoneOffset.UTC));
        assertEquals(flight2ID.getDeparture(),ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 15, 3, 30), ZoneOffset.UTC));
        assertEquals(flight2ID.getDestination(), destinationAirport);
        assertEquals(flight2ID.getOrigin(), originAirport);
        assertEquals(flight2ID.getId(), Long.valueOf(666L));
        assertEquals(flight2ID.getPlane(), airplane);
        assertEquals(flight2ID.getStewards().size(), 1);

        verify(flightDao, times(1)).findById(42L);
        verify(flightDao, times(1)).findById(666L);
    }

    @Test
    public void testFindAll() {

        when(flightDao.findAll()).thenReturn(new ArrayList<>(Arrays.asList(flight1, flight2)));

        List<Flight> allFlights = flightService.findAll();

        assertEquals(allFlights.get(0).getArrival(), ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 9, 1), ZoneOffset.UTC));
        assertEquals(allFlights.get(0).getDeparture(), ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 14, 1), ZoneOffset.UTC));
        assertEquals(allFlights.get(0).getDestination(), destinationAirport);
        assertEquals(allFlights.get(0).getOrigin(), originAirport);
        assertEquals(allFlights.get(0).getId(), Long.valueOf(42L));
        assertEquals(allFlights.get(0).getPlane(), airplane);
        assertEquals(allFlights.get(0).getStewards().size(), 0);

        assertEquals(allFlights.get(1).getArrival(), ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 14, 13, 1), ZoneOffset.UTC));
        assertEquals(allFlights.get(1).getDeparture(),ZonedDateTime.of(LocalDateTime.of(2020, Month.JULY, 15, 3, 30), ZoneOffset.UTC));
        assertEquals(allFlights.get(1).getDestination(), destinationAirport);
        assertEquals(allFlights.get(1).getOrigin(), originAirport);
        assertEquals(allFlights.get(1).getId(), Long.valueOf(666L));
        assertEquals(allFlights.get(1).getPlane(), airplane);
        assertEquals(allFlights.get(1).getStewards().size(), 1);

        verify(flightDao, times(1)).findAll();
    }
}