package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.entity.Airplane;

import java.util.Collection;

/**
 * Service Interface for Airplane
 *
 * @author Lukáš Matta
 */

public interface AirplaneService {
    /**
     * Find Airport by id
     *
     * @param id of Airplane
     * @return found Airplane
     */
    Airplane findById(Long id);

    /**
     * Create new Airplane
     *
     * @param airplane object Airplane
     */
    void createAirplane(Airplane airplane);

    /**
     * Delete airport with given id
     *
     * @param id of Airplane
     */
    void deleteAirplane(Long id);

    /**
     * Update airplane
     *
     * @param airplane to be updated
     */
    void updateAirplane(Airplane airplane);

    /**
     * Find all airplanes
     *
     * @return List of Airplanes
     */
    Collection<Airplane> findAllAirplanes();
}
