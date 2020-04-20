package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.AirportDao;
import cz.muni.fi.pa165.airportmanager.entity.Airport;

import javax.inject.Inject;
import java.util.List;

/**
 * Service for Airport
 *
 * @author Tomáš Janíček
 */

public class AirportServiceImpl implements AirportService {
    @Inject
    private AirportDao airportDao;

    @Override
    public Airport findById(Long id) {
        return airportDao.findById(id);
    }

    @Override
    public void createAirport(Airport airport) {
        airportDao.insertAirport(airport);
    }

    @Override
    public void deleteAirport(Long id) {
        Airport toDelete = airportDao.findById(id);
        airportDao.deleteAirport(toDelete);
    }

    @Override
    public void updateAirport(Airport airport) {
        airportDao.updateAirport(airport);
    }

    @Override
    public List<Airport> findAllAirports() {
        return airportDao.findAll();
    }
}