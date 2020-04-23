package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.entity.Airplane;

import java.time.ZonedDateTime;
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

    /**
     * Finds an airplane in a time interval which not scheduled to any flight
     * @param from Lower-bound of the time interval
     * @param to Upper-bound of the time interval
     * @return Free airplane
     */
    Airplane findFreePlaneInTimeInterval(ZonedDateTime from, ZonedDateTime to);
}
