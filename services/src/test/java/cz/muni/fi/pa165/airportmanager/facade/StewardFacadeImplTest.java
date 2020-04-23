package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.StewardService;
import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Author: Almas Shakirtkhanov
 *
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class StewardFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private StewardService stewardService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private StewardFacadeImpl stewardFacade;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllStewardsTest() {

        List<Steward> stewards = new ArrayList<>();
        List<StewardDTO> stewardDTOS = new ArrayList<>();

        for(int i = 1; i <=5; i++){

            StewardDTO stewardDTO = new StewardDTO();
            stewardDTO.setId((long) i);
            stewardDTO.setFirstName("Almas" + i);
            stewardDTO.setLastName("Luffy" + i);
            stewardDTOS.add(stewardDTO);

            Steward steward = new Steward((long)i);
            steward.setFirstName("Almas" + i);
            steward.setLastName("Luffy" + i);
            stewards.add(steward);
        }


        when(stewardService.findAll()).thenReturn(stewards);
        when(beanMappingService.mapToList(stewards, StewardDTO.class)).thenReturn(stewardDTOS);


        List<StewardDTO> stewardDTOList = stewardFacade.findAll();

        assertEquals(stewardDTOList.size(), 5);
        assertEquals(stewardDTOList.get(3).getFirstName(), "Almas4");
        assertEquals(stewardDTOList.get(3).getLastName(), "Luffy4");


        verify(stewardService).findAll();
    }

    @Test
    public void createStewardTest() {
        StewardDTO stewardDTO = new StewardDTO();
        stewardDTO.setId((long) 1);
        stewardDTO.setFirstName("Almas");
        stewardDTO.setLastName("Luffy");

        Steward steward = new Steward((long)1);
        steward.setFirstName("Almas");
        steward.setLastName("Luffy");


        when(beanMappingService.mapTo(stewardDTO, Steward.class)).thenReturn(steward);

        stewardFacade.insertSteward(stewardDTO);

        assertEquals(stewardDTO.getFirstName(), steward.getFirstName());
        assertEquals(stewardDTO.getLastName(), steward.getLastName());

        verify(stewardService).insertSteward(steward);
    }

    @Test
    public void updateStewardTest() {
        StewardDTO stewardDTO = new StewardDTO();
        stewardDTO.setId((long) 1);
        stewardDTO.setFirstName("Almas");
        stewardDTO.setLastName("Luffy");

        Steward steward = new Steward((long)1);
        steward.setFirstName("Almas");
        steward.setLastName("Luffy");


        when(beanMappingService.mapTo(stewardDTO, Steward.class)).thenReturn(steward);

        stewardFacade.updateSteward(stewardDTO);

        assertEquals(stewardDTO.getFirstName(), steward.getFirstName());
        assertEquals(stewardDTO.getLastName(), steward.getLastName());

        verify(stewardService).updateSteward(steward);
    }

    @Test
    public void deleteStewardTest() {
        stewardFacade.deleteSteward((long) 1);
        verify(stewardService).deleteSteward((long) 1);
    }

    @Test
    public void findStewardByIdTest() {
        StewardDTO stewardDTO = new StewardDTO();
        stewardDTO.setId((long) 1);
        stewardDTO.setFirstName("Almas");
        stewardDTO.setLastName("Luffy");

        Steward steward = new Steward((long)1);
        steward.setFirstName("Almas");
        steward.setLastName("Luffy");

        when(beanMappingService.mapTo(steward, StewardDTO.class)).thenReturn(stewardDTO);

        when(stewardService.findById((long) 1)).thenReturn(steward);
        when(stewardService.findById((long) 2)).thenReturn(null);

        StewardDTO stewardDTO1 = stewardFacade.findById((long) 1);
        assertEquals(stewardDTO1.getFirstName(), stewardDTO.getFirstName());
        assertEquals(stewardDTO1.getLastName(), stewardDTO.getLastName());

        StewardDTO stewardDTO2 = stewardFacade.findById((long) 2);
        assertNull(stewardDTO2);
        verify(stewardService, times(1)).findById((long) 1);
        verify(stewardService, times(1)).findById((long) 2);
    }

}