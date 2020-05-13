package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.UserService;
import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Facade for User
 *
 * @author Tomáš Janíček
 */

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findByName(String name) {
        User user = userService.findByName(name);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        return beanMappingService.mapToList(users, UserDTO.class);
    }

    @Override
    public UserDTO getCurrentUser() {
        return beanMappingService.mapTo(userService.getCurrentUser(), UserDTO.class);
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public UserAuthenticateDTO login(String name, String password) {
        User user = userService.login(name, password);
        return beanMappingService.mapTo(user, UserAuthenticateDTO.class);
    }

    @Override
    public boolean isAdmin(UserDTO user) {
        return userService.isAdmin(beanMappingService.mapTo(user, User.class));
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO user) {
        return userService.authenticate(
                userService.findById(user.getId()), user.getPassword());
    }
}
