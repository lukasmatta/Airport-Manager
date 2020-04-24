package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
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
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId((long) 5);
        flightDTO.setDeparture(testDate);

        Flight flight = new Flight();
        flight.setId((long) 5);
        flight.setDeparture(testDate);

        when(beanMappingService.mapTo(flightDTO, Flight.class)).thenReturn(flight);

        flightFacade.create(flightDTO);

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