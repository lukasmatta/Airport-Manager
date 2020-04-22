package cz.muni.fi.pa165.airportmanager.dao;



import cz.muni.fi.pa165.airportmanager.entity.Airplane;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * DAO for Airplane
 *
 * @author Tomáš Janíček
 */
public interface AirplaneDao {
    /**
     * Finds Airplane by id
     *
     * @param id of airplane
     * @return found Airplane
     */
    Airplane findById(Long id);

    /**
     * Finds all airplanes
     *
     * @return list of all airplanes
     */
    List<Airplane> findAll();

    /**
     * Inserts airplane into DB
     *
     * @param airplane Airplane object to create
     */
    Long insertAirplane(Airplane airplane);

    /**
     * Updates airplane in DB
     *
     * @param airplane Airplane object to update
     */
    void updateAirplane(Airplane airplane);

    /**
     * Deletes airplane from DB
     *
     * @param airplane Airplane object to delete
     */
    void deleteAirplane(Airplane airplane);
}
