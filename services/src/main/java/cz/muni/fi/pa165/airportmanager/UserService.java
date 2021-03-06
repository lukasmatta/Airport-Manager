package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.entity.User;

import java.util.List;

/**
 * Service interface for User
 *
 * @author Tomáš Janíček
 */
public interface UserService {
    /**
     * Finds User by id
     *
     * @param id of User
     * @return found User
     */
    User findById(Long id);

    /**
     * Finds User by name
     *
     * @param name of User
     * @return found User
     */
    User findByName(String name);

    /**
     * Finds all Users
     *
     * @return list of all Users
     */
    List<User> findAll();

    /**
     * Returns current logged User
     *
     * @return current logged user
     */
    User getCurrentUser();

    /**
     * Register User
     * @param user to register
     * @param password password of user
     * @return id of user
     */
    Long register(User user, String password);

    /**
     * Authenticate User
     *
     * @param user User object to authenticate
     */
    boolean authenticate(User user, String password);

    /**
     * Check if current user is admin
     *
     * @param user User object to check
     */
    boolean isAdmin(User user);
}
