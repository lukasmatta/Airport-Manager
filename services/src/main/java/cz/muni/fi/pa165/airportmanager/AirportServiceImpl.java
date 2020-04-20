package cz.muni.fi.pa165.airportmanager;

import javax.inject.Inject;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.entity.Airport;

import java.util.List;

/**
 * Service for Airport
 *
 * @author Tomáš Janíček
 */

public class AirportServiceImpl implements AirportService {
    @Inject
    private AirplaneDao airplaneDao;

    @Override
    public Airport findById(Long id) {
        return null;
    }

    @Override
    public void createAirport(Airport airport) {

    }

    @Override
    public void deleteAirport(Long id) {

    }

    @Override
    public void updateAirport(Airport airport) {

    }

    @Override
    public List<Airport> findAllAirports() {
        return null;
    }
}
