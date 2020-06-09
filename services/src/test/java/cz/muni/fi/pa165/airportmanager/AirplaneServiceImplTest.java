package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import cz.muni.fi.pa165.airportmanager.exceptions.OverlappingTimeException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Author: Lukáš Matta
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AirplaneServiceImplTest extends AbstractTestNGSpringContextTests {
    @Mock
    private AirplaneDao airplaneDao;

    @Autowired
    @InjectMocks
    private AirplaneService airplaneService;

    private Airplane airplane1 = new Airplane((long) 1);
    private Airplane airplane2 = new Airplane((long) 2);
    private List<Airplane> airplanes;

    @BeforeMethod
    public void createAirplanesAndFlights() throws OverlappingTimeException {
        Airport dummyAirport = new Airport();
        dummyAirport.setCountry("Slovakia");
        dummyAirport.setCity("KSC");
        Flight flight1 = new Flight();
        flight1.setId((long) 1);
        flight1.setPlane(airplane1);
        flight1.setOrigin(dummyAirport);
        flight1.setDestination(dummyAirport);
        flight1.setStewards(Collections.emptySet());
        flight1.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,0), ZoneOffset.UTC));
        flight1.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,0), ZoneOffset.UTC));

        Flight flight2 = new Flight();
        flight2.setId((long) 2);
        flight2.setPlane(airplane2);
        flight2.setOrigin(dummyAirport);
        flight2.setDestination(dummyAirport);
        flight2.setStewards(Collections.emptySet());
        flight2.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,15,0), ZoneOffset.UTC));
        flight2.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,17,0), ZoneOffset.UTC));

        // Airplane1 is occupied from 14:00 to 16:00 on 1st MAY 2020
        airplane1.setCapacity(100);
        airplane1.setName("Boeing 737");
        airplane1.setType(AirplaneType.COMMERCIAL);
        airplane1.setFlights(Collections.singleton(flight1));

        // Airplane2 is occupied from 15:00 to 17:00 on 1st MAY 2020
        airplane2.setCapacity(120);
        airplane2.setName("Airbus A320");
        airplane2.setType(AirplaneType.COMMERCIAL);
        airplane2.setFlights(Collections.singleton(flight2));

        airplanes = Arrays.asList(airplane1, airplane2);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findFreePlaneInTimeIntervalTest() {
        when(airplaneDao.findAll()).thenReturn(airplanes);

        ZonedDateTime from1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,1), ZoneOffset.UTC);
        ZonedDateTime to1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,17,0), ZoneOffset.UTC);
        Airplane freeAirplane1 = airplaneService.findFreePlaneInTimeInterval(from1, to1);

        Assert.assertEquals(freeAirplane1, airplane1);

        ZonedDateTime from2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,1), ZoneOffset.UTC);
        ZonedDateTime to2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,59), ZoneOffset.UTC);
        Airplane freeAirplane2 = airplaneService.findFreePlaneInTimeInterval(from2, to2);

        Assert.assertEquals(freeAirplane2, airplane2);

        ZonedDateTime from3 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,15,0), ZoneOffset.UTC);
        ZonedDateTime to3 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,0), ZoneOffset.UTC);
        Airplane freeAirplane3 = airplaneService.findFreePlaneInTimeInterval(from3, to3);

        Assert.assertNull(freeAirplane3);

        verify(airplaneDao, times(3)).findAll();
    }
}