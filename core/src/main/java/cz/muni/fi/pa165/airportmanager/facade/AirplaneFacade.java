package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;

import java.util.List;

/**
 * Facade for Airplane
 *
 * @author Lukáš Matta
 */

public interface AirplaneFacade {
    /**
     * Find Airplane by id
     *
     * @param id of Airplane
     * @return found Airplane
     */
    AirplaneDTO findById(Long id);

    /**
     * Delete airplane with given id
     *
     * @param id of Airplane
     */
    void deleteAirplane(Long id);

    /**
     * Create new Airplane
     *
     * @param airplane object Airplane
     */
    void createAirplane(AirplaneDTO airplane);

    /**
     * Update airplane
     *
     * @param airplane to be updated
     */
    void updateAirplane(AirplaneDTO airplane);

    /**
     * Find all airplanes
     *
     * @return List of Airplanes
     */
    List<AirplaneDTO> findAllAirplanes();
}
