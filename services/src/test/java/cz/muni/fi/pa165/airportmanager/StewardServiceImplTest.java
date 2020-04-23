package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import cz.muni.fi.pa165.airportmanager.enums.AirplaneType;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Author: Almas Shakirtkhanov
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class StewardServiceImplTest {
    @Mock
    private StewardDao stewardDao;

    @Autowired
    @InjectMocks
    private StewardService stewardService;

    private Steward steward1 = new Steward();
    private Steward steward2 = new Steward();
    private Steward steward3 = new Steward();

    private List<Steward> stewards;

    @BeforeMethod
    public void initObjects(){
        //First Airport
        Airport airport1 = new Airport();
        airport1.setCountry("Kazakhstan");
        airport1.setCity("Almaty");

        //Second Airport
        Airport airport2 = new Airport();
        airport1.setCountry("Kazakhstan");
        airport1.setCity("Astana");

        Airplane airplane = new Airplane();
        airplane.setCapacity(100);
        airplane.setName("Boeing 737");
        airplane.setType(AirplaneType.COMMERCIAL);


        steward1.setFirstName("Almas");
        steward1.setLastName("Luffy");

        steward2.setFirstName("Beka");
        steward2.setLastName("Kokpanbaev");

        steward3.setFirstName("Aza");
        steward3.setLastName("Ergali");

        stewards = Arrays.asList(steward1, steward2, steward3);

        Flight flight1 = new Flight();
        flight1.setId((long) 1);
        flight1.setPlane(airplane);
        flight1.setOrigin(airport1);
        flight1.setDestination(airport2);
        flight1.addSteward(steward1);
        flight1.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,0), ZoneOffset.UTC));
        flight1.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,0), ZoneOffset.UTC));

        Flight flight2 = new Flight();
        flight2.setId((long) 2);
        flight2.setPlane(airplane);
        flight2.setOrigin(airport2);
        flight2.setDestination(airport1);
        flight2.addSteward(steward2);
        flight2.setDeparture(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,15,0), ZoneOffset.UTC));
        flight2.setArrival(ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,17,0), ZoneOffset.UTC));

    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findFreeStewardInTimeIntervalTest() {
        when(stewardDao.findAll()).thenReturn(stewards);

        ZonedDateTime from1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,1), ZoneOffset.UTC);
        ZonedDateTime to1 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,17,0), ZoneOffset.UTC);
        Steward freeSteward1 = stewardService.findFreeStewardInTimeInterval(from1, to1);

        Assert.assertEquals(freeSteward1, steward1);

        ZonedDateTime from2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,1), ZoneOffset.UTC);
        ZonedDateTime to2 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,14,59), ZoneOffset.UTC);
        Steward freeSteward2 = stewardService.findFreeStewardInTimeInterval(from2, to2);

        Assert.assertEquals(freeSteward2, steward2);

        ZonedDateTime from3 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,15,0), ZoneOffset.UTC);
        ZonedDateTime to3 = ZonedDateTime.of(LocalDateTime.of(2020, Month.MAY, 1,16,0), ZoneOffset.UTC);
        Steward freeSteward3 = stewardService.findFreeStewardInTimeInterval(from3, to3);

        Assert.assertNull(freeSteward3);

        verify(stewardDao, times(3)).findAll();
    }


}