package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Airport;

import java.util.List;

/**
 * Author: Lukas Matta
 */
public interface AirportDAO {
    /**
     * Creates new airport
     * @param airport
     * @return
     */
    public Long insertAirport(Airport airport);

    /**
     * Updates existing airport
     * @param airport
     * @return
     */
    public Airport updateAirport(Airport airport);

    /**
     * Removes existing airport
     * @param airport
     */
    public void deleteAirport(Airport airport);

    /**
     * Find airport by it's ID
     * @param id
     * @return
     */
    public Airport findById(Long id);

    /**
     * Returns list of all existing airports
     * @return
     */
    public List<Airport> findAll();
}
