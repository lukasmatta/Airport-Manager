package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
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
    Long createAirplane(AirplaneCreateDTO airplane);

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

    /**
     * Finds an airplane in a time interval which not scheduled to any flight
     * @param from Lower-bound of the time interval
     * @param to Upper-bound of the time interval
     * @return Free airplane
     */
    AirplaneDTO findFreePlaneInTimeInterval(ZonedDateTime from, ZonedDateTime to);
}
