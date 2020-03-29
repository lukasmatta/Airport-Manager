package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Flight;

import java.util.List;

/**
 * @author Petr Kantek
 */
public interface FlightDao {
    void create(Flight entity);

    void delete(Flight entity);

    void deleteById(long entityId);

    Flight update(Flight entity);

    Flight findById(long id);

    List<Flight> findAll();
}
