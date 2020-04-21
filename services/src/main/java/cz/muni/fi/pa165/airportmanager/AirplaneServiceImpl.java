package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Service for Airplane
 *
 * @author Lukáš Matta
 */

@Service
public class AirplaneServiceImpl implements AirplaneService {
    @Inject
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
}
