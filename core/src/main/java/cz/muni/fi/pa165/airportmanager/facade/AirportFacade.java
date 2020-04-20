package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;

import java.util.List;

/**
 * Facade for Airport
 *
 * @author Tomáš Janíček
 */

public interface AirportFacade {
    /**
     * Find Airport by id
     *
     * @param id of Airport
     * @return found Airport
     */
    AirportDTO findById(Long id);

    /**
     * Delete airport with given id
     *
     * @param id of Airport
     */
    void deleteAirport(Long id);

    /**
     * Create new Airport
     *
     * @param airport object Airport
     */
    void createAirport(AirportDTO airport);

    /**
     * Update airport
     *
     * @param airport to be updated
     */
    void updateAirport(AirportDTO airport);

    /**
     * Find all airports
     *
     * @return List of Airports
     */
    List<AirportDTO> findAllAirports();
}
