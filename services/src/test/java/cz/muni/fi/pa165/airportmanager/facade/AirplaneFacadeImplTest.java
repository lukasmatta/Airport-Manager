package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirplaneService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * Mock testing of basic CRUD Airplane operations on the facade layer
 *
 * @author Petr Kantek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AirplaneFacadeImplTest {

    @Mock
    private AirplaneService airplaneService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private AirplaneFacadeImpl airplaneFacade;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Airplane airEntity = new Airplane();
        airEntity.setName("Boeing");
        airEntity.setCapacity(42);
        airEntity.setFlights(new HashSet<>());
        airEntity.setType(AirplaneType.COMMERCIAL);

        AirplaneDTO airDTO = new AirplaneDTO();
        airDTO.setName("Boeing");
        airDTO.setCapacity(42);
        airDTO.setFlights(new HashSet<>());
        airDTO.setType(AirplaneType.COMMERCIAL);

        when(airplaneService.findById(42L)).thenReturn(airEntity);
        when(airplaneService.findById(666L)).thenReturn(null);
        when(beanMappingService.mapTo(airEntity, AirplaneDTO.class)).thenReturn(airDTO);

        AirplaneDTO foundAir1 = airplaneFacade.findById(42L);
        AirplaneDTO foundAir2 = airplaneFacade.findById(666L);
        assertEquals(foundAir1.getCapacity(), 42, "Found plane has different capacity");
        assertEquals(foundAir1.getName(), "Boeing", "Found plane has different name");
        assertEquals(foundAir1.getType(), AirplaneType.COMMERCIAL, "Found plane is of different type");
        assertEquals(foundAir1.getFlights().size(), 0, "Found plane is assigned to some flights");

        Assert.isNull(foundAir2, "Searching for a non-existent plane should return null");
        verify(beanMappingService, times(1)).mapTo(airEntity, AirplaneDTO.class);
        verify(airplaneService, times(1)).findById(42L);
        verify(airplaneService, times(1)).findById(666L);
    }

    @Test
    public void testDeleteAirplane() {
        airplaneFacade.deleteAirplane(42L);
        verify(airplaneService, times(1)).deleteAirplane(42L);
    }

    @Test
    public void testCreateAirplane() {
        Airplane airEntity = new Airplane();
        airEntity.setName("Boeing");
        airEntity.setCapacity(42);
        airEntity.setFlights(new HashSet<>());
        airEntity.setType(AirplaneType.COMMERCIAL);

        AirplaneCreateDTO airCreateDTO = new AirplaneCreateDTO();
        airCreateDTO.setName("Boeing");
        airCreateDTO.setCapacity(42);
        airCreateDTO.setType(AirplaneType.COMMERCIAL);

        when(beanMappingService.mapTo(airCreateDTO, Airplane.class)).thenReturn(airEntity);
        airplaneService.createAirplane(airEntity);
        verify(airplaneService, times(1)).createAirplane(airEntity);
    }

    @Test
    public void testUpdateAirplane() {
        Airplane airEntity = new Airplane();
        airEntity.setName("Boeing");
        airEntity.setCapacity(42);
        airEntity.setFlights(new HashSet<>());
        airEntity.setType(AirplaneType.COMMERCIAL);

        AirplaneDTO airDTO = new AirplaneDTO();
        airDTO.setName("Boeing");
        airDTO.setCapacity(42);
        airDTO.setFlights(new HashSet<>());
        airDTO.setType(AirplaneType.COMMERCIAL);

        when(beanMappingService.mapTo(airDTO, Airplane.class)).thenReturn(airEntity);
        airplaneService.updateAirplane(airEntity);
        verify(airplaneService, times(1)).updateAirplane(airEntity);
    }

    @Test
    public void testFindAllAirplanes() {
        Airplane airEntity = new Airplane();
        airEntity.setName("Boeing");
        airEntity.setCapacity(42);
        airEntity.setFlights(new HashSet<>());
        airEntity.setType(AirplaneType.COMMERCIAL);

        AirplaneDTO airDTO = new AirplaneDTO();
        airDTO.setName("Boeing");
        airDTO.setCapacity(42);
        airDTO.setFlights(new HashSet<>());
        airDTO.setType(AirplaneType.COMMERCIAL);

        List<Airplane> airplaneList = Collections.singletonList(airEntity);
        List<AirplaneDTO> airplaneDTOList = Collections.singletonList(airDTO);

        when(airplaneService.findAllAirplanes()).thenReturn(airplaneList);
        when(beanMappingService.mapToList(airplaneList, AirplaneDTO.class)).thenReturn(airplaneDTOList);

        List<AirplaneDTO> foundPlanes = airplaneFacade.findAllAirplanes();

        assertEquals(foundPlanes.get(0).getCapacity(), 42, "Found plane has different capacity");
        assertEquals(foundPlanes.get(0).getName(), "Boeing", "Found plane has different name");
        assertEquals(foundPlanes.get(0).getType(), AirplaneType.COMMERCIAL, "Found plane is of different type");
        assertEquals(foundPlanes.get(0).getFlights().size(), 0, "Found plane is assigned to some flights");

        verify(airplaneService, times(1)).findAllAirplanes();
        verify(beanMappingService, times(1)).mapToList(airplaneList, AirplaneDTO.class);
    }

    @Test
    public void testFindFreePlaneInTimeInterval() {
        Airplane airEntity = new Airplane();
        airEntity.setName("Boeing");
        airEntity.setCapacity(42);
        airEntity.setFlights(new HashSet<>());
        airEntity.setType(AirplaneType.COMMERCIAL);

        AirplaneDTO airDTO = new AirplaneDTO();
        airDTO.setName("Boeing");
        airDTO.setCapacity(42);
        airDTO.setFlights(new HashSet<>());
        airDTO.setType(AirplaneType.COMMERCIAL);

        ZonedDateTime testDateFrom = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 9, 1), ZoneOffset.UTC);
        ZonedDateTime testDateTo = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1, 14, 1), ZoneOffset.UTC);

        when(airplaneService.findFreePlaneInTimeInterval(testDateFrom, testDateTo)).thenReturn(airEntity);
        when(beanMappingService.mapTo(airEntity, AirplaneDTO.class)).thenReturn(airDTO);

        AirplaneDTO freePlane = airplaneFacade.findFreePlaneInTimeInterval(testDateFrom, testDateTo);
        assertEquals(freePlane.getCapacity(), 42, "Found plane has different capacity");
        assertEquals(freePlane.getName(), "Boeing", "Found plane has different name");
        assertEquals(freePlane.getType(), AirplaneType.COMMERCIAL, "Found plane is of different type");
        assertEquals(freePlane.getFlights().size(), 0, "Found plane is assigned to some flights");

        verify(airplaneService, times(1)).findFreePlaneInTimeInterval(testDateFrom, testDateTo);
        verify(beanMappingService, times(1)).mapTo(airEntity, AirplaneDTO.class);
    }
}