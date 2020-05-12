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
     * Authenticate User
     *
     * @param user UserDTO object to authenticate
     */
    boolean authenticate(UserAuthenticateDTO user);

    /**
     * Check if User is admin
     *
     * @param user UserDTO object to check
     */
    boolean isAdmin(UserDTO user);
}
