package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirplaneService;
import cz.muni.fi.pa165.airportmanager.AirportService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Tests for FlightFacade
 *
 * @author Tomáš Janíček
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class FlightFacadeImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private FlightService flightService;


    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private FlightFacadeImpl flightFacade;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private final ZonedDateTime testDate = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 16, 1), ZoneOffset.UTC);

    @Test
    public void findByIdTest() {
        Flight flight = new Flight();
        flight.setId((long) 5);
        flight.setDeparture(testDate);

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId((long) 5);
        flight.setDeparture(testDate);

        when(beanMappingService.mapTo(flight, FlightDTO.class)).thenReturn(flightDTO);

        when(flightService.findById(5)).thenReturn(flight);
        when(flightService.findById(6)).thenReturn(null);

        FlightDTO testFlight = flightFacade.findById(5);
        assertEquals(testFlight.getDeparture(), flightDTO.getDeparture());

        FlightDTO none = flightFacade.findById(6);
        assertNull(none);
        verify(flightService, times(1)).findById(5);
        verify(flightService, times(1)).findById(6);
    }

    @Test
    public void deleteFlightTest() {
        flightFacade.deleteById(5);
        verify(flightService).deleteById(5);
    }

    @Test
    public void testCreateFlight() {
        FlightCreateDTO flightCreateDTO= new FlightCreateDTO();

        ZonedDateTime dep = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 18,12,1), ZoneOffset.UTC);
        ZonedDateTime arr = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 18,13,0), ZoneOffset.UTC);

        flightCreateDTO.setDeparture(dep);
        flightCreateDTO.setArrival(arr);

        flightCreateDTO.setDestinationID(4);
        flightCreateDTO.setOriginID(3);

        flightCreateDTO.setPlaneID(1);

        Flight flight = new Flight();

        ZonedDateTime dep_1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 18,12,1), ZoneOffset.UTC);
        ZonedDateTime arr_1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.AUGUST, 18,13,0), ZoneOffset.UTC);

        flight.setDeparture(dep);
        flight.setArrival(arr);

        Airport air1 = new Airport((long)3);
        air1.setCity("Almaty");
        air1.setCountry("Kazakhstan");

        Airport air2 = new Airport((long)4);
        air2.setCity("Astana");
        air2.setCountry("Kazakhstan");

        flight.setDestination(air2);
        flight.setOrigin(air1);

        Airplane airplane = new Airplane((long)1);
        airplane.setCapacity(100);
        airplane.setName("Boeing");
        airplane.setType(AirplaneType.COMMERCIAL);
        flight.setPlane(airplane);

        when(beanMappingService.mapTo(flightCreateDTO, Flight.class)).thenReturn(flight);

        flightService.create(flight);

        verify(flightService).create(flight);
    }

    @Test
    public void testFindAllFlights() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId((long) 5);
        flightDTO.setDeparture(testDate);

        Flight flight = new Flight();
        flight.setId((long) 5);
        flight.setDeparture(testDate);

        List<Flight> flights = Collections.singletonList(flight);
        List<FlightDTO> flightDTOS = Collections.singletonList(flightDTO);


        when(flightService.findAll()).thenReturn(flights);
        when(beanMappingService.mapToList(flights, FlightDTO.class)).thenReturn(flightDTOS);


        List<FlightDTO> flightFacadeAll = flightFacade.findAll();

        assertEquals(flightFacadeAll.size(), 1);
        assertEquals(flightFacadeAll.get(0).getDeparture(), testDate);


        verify(flightService).findAll();
    }

    @Test
    public void testUpdateFlight() {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId((long) 5);
        flightDTO.setDeparture(testDate);

        Flight flight = new Flight();
        flight.setId((long) 5);
        flight.setDeparture(testDate);

        when(beanMappingService.mapTo(flightDTO, Flight.class)).thenReturn(flight);

        flightFacade.update(flightDTO);

        verify(flightService).update(flight);
    }
}