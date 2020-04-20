package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import java.util.List;

/**
 * Flight facade interface
 *
 * @author Petr Kantek
 */
public interface FlightFacade {

    /**
     * Inserts flight into DB
     * @param entity flight entity
     * @return created flight ID
     */
    Long create(FlightDTO entity);

    /**
     * Deletes flight from DB
     * @param entity flight entity
     */
    void delete(FlightDTO entity);

    /**
     * Deletes flight with the given ID from DB
     * @param entityId ID of flight to be deleted
     */
    void deleteById(long entityId);

    /**
     * Update attributes of given entity in DB
     * @param entity flight entity
     * @return updated flight
     */
    FlightDTO update(FlightDTO entity);

    /**
     * Finds a flight in DB by its ID
     * @param id ID of the flight to be searched for
     * @return searched flight
     */
    FlightDTO findById(long id);

    /**
     * Finds all flights in DB
     * @return all flights in DB
     */
    List<FlightDTO> findAll();


}
