package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;

import java.util.List;

public interface UserFacade {
    /**
     * Finds User by id
     *
     * @param id of User
     * @return found UserDTO
     */
    UserDTO findById(Long id);

    /**
     * Finds User by name
     *
     * @param name of User
     * @return found UserDTO
     */
    UserDTO findByName(String name);

    /**
     * Finds all Users
     *
     * @return list of all UserDTOs
     */
    List<UserDTO> findAll();

    /**
     * Returns current logged User
     *
     * @return current logged user
     */
    UserDTO getCurrentUser();

    /**
     * Check if User is admin
     *
     * @param user UserDTO object to check
     */
    boolean isAdmin(UserDTO user);

    /**
     * Authenticate user
     *
     * @param u user to authenticate
     * @return result of authorisation
     */
    boolean authenticate(UserAuthenticateDTO u);

    /**
     * Register User
     *
     * @param user user to register
     * @return registered user credentials
     */
    UserDTO register(UserAuthenticateDTO user);
}
