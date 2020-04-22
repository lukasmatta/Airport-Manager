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
     * @param steward
     */
    void deleteSteward(Steward steward);

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
}