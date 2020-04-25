package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dao.AirportDao;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
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

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Mock testing of basic CRUD Airport operations on the service layer
 *
 * @author Petr Kantek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AirportServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AirportDao airportDao;

    @InjectMocks
    private AirportServiceImpl airportService;

    private Airport airport1;
    private Airport airport2;
    private Airport airport3;
    private List<Airport> airports;

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        airport1 = new Airport();
        airport1.setCity("Frankfurt");
        airport1.setCountry("Germany");

        airport2 = new Airport();
        airport2.setCity("Berlin");
        airport2.setCountry("Germany");

        airport3 = new Airport();
        airport3.setCity("Paris");
        airport3.setCountry("France");

        airports = new ArrayList<>(Arrays.asList(airport1, airport2, airport3));
    }

    @Test
    public void testFindById() {

        when(airportDao.findById(42L)).thenReturn(airport1);
        when(airportDao.findById(666L)).thenReturn(airport2);
        when(airportDao.findById(7L)).thenReturn(airport3);

        Airport found1 = airportService.findById(42L);
        Airport found2 = airportService.findById(666L);
        Airport found3 = airportService.findById(7L);

        assertEquals(found1.getCity(), airport1.getCity());
        assertEquals(found1.getCountry(), airport1.getCountry());
        assertEquals(found2.getCity(), airport2.getCity());
        assertEquals(found2.getCountry(), airport2.getCountry());
        assertEquals(found3.getCity(), airport3.getCity());
        assertEquals(found3.getCountry(), airport3.getCountry());

        verify(airportDao, times(1)).findById(42L);
        verify(airportDao, times(1)).findById(666L);
        verify(airportDao, times(1)).findById(7L);
    }

    @Test
    public void testCreateAirport() {

        when(airportDao.insertAirport(airport1)).thenReturn(42L);
        when(airportDao.insertAirport(airport2)).thenReturn(666L);
        when(airportDao.insertAirport(airport3)).thenReturn(7L);

        airportService.createAirport(airport1);
        airportService.createAirport(airport2);
        airportService.createAirport(airport3);

        verify(airportDao, times(1)).insertAirport(airport1);
        verify(airportDao, times(1)).insertAirport(airport2);
        verify(airportDao, times(1)).insertAirport(airport3);
    }

    @Test
    public void testDeleteAirport() {
        when(airportDao.findById(42L)).thenReturn(airport1);
        when(airportDao.findById(666L)).thenReturn(airport2);
        when(airportDao.findById(7L)).thenReturn(airport3);

        airportService.deleteAirport(42L);
        airportService.deleteAirport(666L);
        airportService.deleteAirport(7L);

        verify(airportDao).deleteAirport(airport1);
        verify(airportDao).deleteAirport(airport2);
        verify(airportDao).deleteAirport(airport3);
    }

    @Test
    public void testUpdateAirport() {
        airportService.updateAirport(airport1);
        airportService.updateAirport(airport2);
        airportService.updateAirport(airport3);

        verify(airportDao).updateAirport(airport1);
        verify(airportDao).updateAirport(airport2);
        verify(airportDao).updateAirport(airport3);
    }

    @Test
    public void testFindAllAirports() {

        when(airportDao.findAll()).thenReturn(airports);
        List<String> cities = new ArrayList<>(Arrays.asList("Frankfurt", "Berlin", "Paris"));
        List<String> countries = new ArrayList<>(Arrays.asList("Germany", "Germany", "France"));

        List<Airport> allAirports = airportService.findAllAirports();
        for (int airport = 0; airport < airports.size(); ++airport) {
            assertEquals(allAirports.get(airport).getCity(), cities.get(airport));
            assertEquals(allAirports.get(airport).getCountry(), countries.get(airport));
        }
        verify(airportDao, times(1)).findAll();
    }
}