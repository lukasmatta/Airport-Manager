package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Flight;

import java.util.List;

/**
 * @author Petr Kantek
 */
public interface FlightDao {

    /**
     * Inserts flight into DB
     * @param entity flight entity
     */
    void create(Flight entity);

    /**
     * Deletes flight from DB
     * @param entity flight entity
     */
    void delete(Flight entity);

    /**
     * Deletes flight with the given ID from DB
     * @param entityId ID of flight to be deleted
     */
    void deleteById(long entityId);

    /**
     * Update attributes of given entity in DB
     * @param entity flight entity
     * @return
     */
    Flight update(Flight entity);

    /**
     * Finds a flight in DB by its ID
     * @param id ID of the flight to be searched for
     * @return searched flight
     */
    Flight findById(long id);

    /**
     * Finds all flights in DB
     * @return all flights in DB
     */
    List<Flight> findAll();
}
