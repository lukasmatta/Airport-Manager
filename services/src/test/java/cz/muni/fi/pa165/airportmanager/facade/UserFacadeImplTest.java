package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.UserService;
import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Test for User Facade
 *
 * @author Tomáš Janíček
 */

public class UserFacadeImplTest {

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private UserFacadeImpl userFacade;

    private User user, user2;
    private UserDTO userDTO, user2DTO;
    private UserAuthenticateDTO userAuthenticateDTO;


    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setName("user");
        user.setAdmin(false);

        user2 = new User();
        user2.setName("user2");
        user2.setAdmin(false);

        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAdmin(user.isAdmin());

        user2DTO = new UserDTO();
        user2DTO.setId(user2.getId());
        user2DTO.setName(user2.getName());
        user2DTO.setAdmin(user2.isAdmin());

        userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setName(user.getName());
        userAuthenticateDTO.setPassword("password");
    }

    @Test
    public void findById(){
        given(userService.findById(user.getId())).willReturn(user);
        given(beanMappingService.mapTo(user, UserDTO.class)).willReturn(userDTO);
        UserDTO resultUserDTO = userFacade.findById(user.getId());
        assertEquals(userDTO, resultUserDTO);
        then(userService).should().findById(user.getId());
    }

    @Test
    public void register(){
        given(beanMappingService.mapTo(userAuthenticateDTO, User.class)).willReturn(user);
        userFacade.register(userAuthenticateDTO);
        then(userService).should().register(user, "password");
    }

    @Test
    public void findAllUsers(){
        List<User> users = new ArrayList<>();
        List<UserDTO> userDTOS = new ArrayList<>();

        users.add(user);
        users.add(user2);

        userDTOS.add(userDTO);
        userDTOS.add(user2DTO);

        when(userService.findAll()).thenReturn(users);
        when(beanMappingService.mapToList(users, UserDTO.class)).thenReturn(userDTOS);


        List<UserDTO> userDTOList = userFacade.findAll();

        assertEquals(userDTOList.size(), 2);
        assertEquals(userDTOList.get(0).getName(), "user");
        assertEquals(userDTOList.get(1).getName(), "user2");


        verify(userService).findAll();
    }
}
