package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.entity.Steward;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Interface for Steward
 *
 * @author Almas Shakirtkhanov
 */

public interface StewardService {
    /**
     * Creates new steward
     * @param steward
     * @return created steward ID
     */
    Long insertSteward(Steward steward);


    /**
     * Updates existing steward
     * @param steward
     */
    void updateSteward(Steward steward);

    /**
     * Removes existing steward
     * @param id of Steward
     */
    void deleteSteward(Long id);

    /**
     * Find steward by it's ID
     * @param id
     * @return entity Steward by id
     */
    Steward findById(Long id);

    /**
     * Returns list of all existing stewards
     * @return  List of Stewards
     */
    List<Steward> findAll();
    /**
     * Returns stewards who is available for the flight
     * @param from Lower-bound of the time interval
     * @param to Upper-bound of the time interval
     * @return entity Steward
     */
    Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to);
}