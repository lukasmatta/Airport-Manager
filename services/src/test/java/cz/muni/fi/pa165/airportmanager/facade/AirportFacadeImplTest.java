package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirportService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.AirportCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import org.hibernate.service.spi.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.testng.Assert.*;

/**
 * Author: Lukáš Matta
 *
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AirportFacadeImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private AirportService airportService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private AirportFacadeImpl airportFacade;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {
        Airport airportEntity = new Airport(5);
        airportEntity.setCity("Kosice");
        airportEntity.setCountry("Slovakia");

        AirportDTO dummyAirport = new AirportDTO();
        dummyAirport.setId(5L);
        dummyAirport.setCity("Kosice");
        dummyAirport.setCountry("Slovakia");

        when(beanMappingService.mapTo(airportEntity, AirportDTO.class)).thenReturn(dummyAirport);

        when(airportService.findById(5L)).thenReturn(airportEntity);
        when(airportService.findById(6L)).thenReturn(null);

        AirportDTO airport = airportFacade.findById(5L);
        assertEquals(airport.getCity(), dummyAirport.getCity());
        assertEquals(airport.getCountry(), dummyAirport.getCountry());

        AirportDTO none = airportFacade.findById(6L);
        assertNull(none);
        verify(airportService, times(1)).findById(5L);
        verify(airportService, times(1)).findById(6L);
    }

    @Test
    public void deleteAirportTest() {
        airportFacade.deleteAirport((long) 5);
        verify(airportService).deleteAirport((long) 5);
    }

    @Test
    public void testCreateAirport() {
        AirportCreateDTO dummyAirport = new AirportCreateDTO();
        dummyAirport.setCity("Kosice");
        dummyAirport.setCountry("Slovakia");

        Airport airportEntity = new Airport(5);
        airportEntity.setCity("Kosice");
        airportEntity.setCountry("Slovakia");

        when(beanMappingService.mapTo(dummyAirport, Airport.class)).thenReturn(airportEntity);

        airportFacade.createAirport(dummyAirport);

        verify(airportService).createAirport(airportEntity);
    }

    @Test
    public void testUpdateAirport() {
        AirportDTO dummyAirport = new AirportDTO();
        dummyAirport.setId(5L);
        dummyAirport.setCity("Kosice");
        dummyAirport.setCountry("Slovakia");

        Airport airportEntity = new Airport(5);
        airportEntity.setCity("Kosice");
        airportEntity.setCountry("Slovakia");

        when(beanMappingService.mapTo(dummyAirport, Airport.class)).thenReturn(airportEntity);

        airportFacade.updateAirport(dummyAirport);

        verify(airportService).updateAirport(airportEntity);
    }

    @Test
    public void testFindAllAirports() {
        AirportDTO dummyAirport = new AirportDTO();
        dummyAirport.setId(5L);
        dummyAirport.setCity("Kosice");
        dummyAirport.setCountry("Slovakia");

        Airport airportEntity = new Airport(5);
        airportEntity.setCity("Kosice");
        airportEntity.setCountry("Slovakia");

        List<Airport> airports = Collections.singletonList(airportEntity);
        List<AirportDTO> airportsDTO = Collections.singletonList(dummyAirport);


        when(airportService.findAllAirports()).thenReturn(airports);
        when(beanMappingService.mapToList(airports, AirportDTO.class)).thenReturn(airportsDTO);


        List<AirportDTO> airportsTest = airportFacade.findAllAirports();

        assertEquals(airportsTest.size(), 1);
        assertEquals(airportsTest.get(0).getCity(), "Kosice");
        assertEquals(airportsTest.get(0).getCountry(), "Slovakia");


        verify(airportService).findAllAirports();
    }
}