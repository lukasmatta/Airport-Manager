package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.User;
import java.util.List;

/**
 * DAO interface for User role
 *
 * @author Tomáš Janíček
 */
public interface UserDao {
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
     * Inserts User into DB
     *
     * @param user User object to create
     */
    Long insertUser(User user);

    /**
     * Updates User in DB
     *
     * @param user User object to update
     */
    void updateUser(User user);

    /**
     * Deletes User from DB
     *
     * @param user User object to delete
     */
    void deleteUser(User user);
}
