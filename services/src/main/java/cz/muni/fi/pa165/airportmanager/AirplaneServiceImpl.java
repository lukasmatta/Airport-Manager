package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for Airplane
 *
 * @author Lukáš Matta
 */

@Service
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private AirplaneDao airplaneDao;

    @Override
    public Airplane findById(Long id) {
        return airplaneDao.findById(id);
    }

    @Override
    public void createAirplane(Airplane airplane) {
        airplaneDao.insertAirplane(airplane);
    }

    @Override
    public void deleteAirplane(Long id) {
        Airplane toDelete = airplaneDao.findById(id);
        airplaneDao.deleteAirplane(toDelete);
    }

    @Override
    public void updateAirplane(Airplane airplane) {
        airplaneDao.updateAirplane(airplane);
    }

    @Override
    public Collection<Airplane> findAllAirplanes() {
        return airplaneDao.findAll();
    }

    @Override
    public Airplane findFreePlaneInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        List<Airplane> allAirplanes = airplaneDao.findAll();
        Optional<Airplane> result = allAirplanes.stream()
                .filter(airplane -> airplane.getFlights()
                        .stream().anyMatch(flight ->
                                flight.getDeparture().compareTo(from) < 0 &&
                                        flight.getArrival().compareTo(from) < 0 ||
                                        flight.getDeparture().compareTo(to) > 0)
                ).findFirst();
        return result.orElse(null);
    }
}
