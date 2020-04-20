package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.entity.Airport;

import java.util.List;

/**
 * Service Interface for Airport
 *
 * @author Tomáš Janíček
 */

public interface AirportService {
    /**
     * Find Airport by id
     *
     * @param id of Airport
     * @return found Airport
     */
    Airport findById(Long id);

    /**
     * Create new Airport
     *
     * @param airport object Airport
     */
    void createAirport(Airport airport);

    /**
     * Delete airport with given id
     *
     * @param id of Airport
     */
    void deleteAirport(Long id);

    /**
     * Update airport
     *
     * @param airport to be updated
     */
    void updateAirport(Airport airport);

    /**
     * Find all airports
     *
     * @return List of Airports
     */
    List<Airport> findAllAirports();
}
