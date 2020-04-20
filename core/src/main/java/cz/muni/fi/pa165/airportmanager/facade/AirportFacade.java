package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.AirportDto;

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
    AirportDto findById(Long id);

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
    void createAirport(AirportDto airport);

    /**
     * Update airport
     *
     * @param airport to be updated
     */
    void updateAirport(AirportDto airport);

    /**
     * Find all airports
     *
     * @return List of Airports
     */
    List<AirportDto> findAllAirports();
}
