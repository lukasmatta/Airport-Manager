package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Steward;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Author: Almas Shakirtkhanov
 */

public interface StewardDao {
    /**
     * Creates new airport
     * @param steward
     * @return
     */
    Long insertSteward(Steward steward);


    /**
     * Updates existing airport
     * @param steward
     * @return
     */
    void updateSteward(Steward steward);

    /**
     * Removes existing airport
     * @param steward
     */
    void deleteSteward(Steward steward);

    /**
     * Find steward by it's ID
     * @param id
     * @return
     */
    Steward findById(Long id);

    /**
     * Returns list of all existing stewards
     * @return
     */
    List<Steward> findAll();

    /**
     * Returns steward who is available for the flight
     * @return
     */
    Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to);
}