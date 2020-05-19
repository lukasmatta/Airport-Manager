package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BeanMappingServiceImpl beanMappingService;

//    @Test
//    public void testFlightMapping() {
//        Steward steward1 = new Steward();
//        steward1.setFirstName("Lukas");
//        steward1.setLastName("Matta");
//
//        Set<Steward> stewards1 = new HashSet<>();
//        stewards1.add(steward1);
//
//        Flight flight1 = new Flight();
//        Set<Flight> flights1 = new HashSet<>();
//        flights1.add(flight1);
//        steward1.setFlights(flights1);
//
//        flight1.setStewards(stewards1);
//
//        StewardDTO steward2 = new StewardDTO();
//        steward2.setFirstName("Lukas");
//        steward2.setLastName("Matta");
//
//        Set<StewardDTO> stewards2 = new HashSet<>();
//        stewards2.add(steward2);
//
//        FlightDTO flight2 = new FlightDTO();
//        Set<FlightDTO> flights2 = new HashSet<>();
//        steward2.setFlights(flights2);
//
//        flight2.setStewards(stewards2);
//
//        FlightDTO mapped = beanMappingService.mapTo(flight1, FlightDTO.class);
//        assertEquals(mapped, flight2);
//    }
//
//    @Test
//    public void testStewardMapping() {
//        Steward steward1 = new Steward();
//        steward1.setFirstName("Lukas");
//        steward1.setLastName("Matta");
//
//        Set<Steward> stewards1 = new HashSet<>();
//        stewards1.add(steward1);
//
//        Flight flight1 = new Flight();
//        Set<Flight> flights1 = new HashSet<>();
//        flights1.add(flight1);
//        steward1.setFlights(flights1);
//
//        flight1.setStewards(stewards1);
//
//        StewardDTO steward2 = new StewardDTO();
//        steward2.setFirstName("Lukas");
//        steward2.setLastName("Matta");
//
//        Set<StewardDTO> stewards2 = new HashSet<>();
//        stewards2.add(steward2);
//
//        FlightDTO flight2 = new FlightDTO();
//        Set<FlightDTO> flights2 = new HashSet<>();
//        steward2.setFlights(flights2);
//
//        flight2.setStewards(stewards2);
//
//        StewardDTO mapped = beanMappingService.mapTo(steward1, StewardDTO.class);
//        assertEquals(mapped, steward2);
//    }
}
