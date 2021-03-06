package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service for Steward
 *
 * @author Almas Shakirtkhanov
 */

@Service
@Transactional
public class StewardServiceImpl implements StewardService {

    @Autowired
    private StewardDao stewardDao;

    @Override
    public Long insertSteward(Steward entity) {
        return stewardDao.insertSteward(entity);
    }

    @Override
    public void updateSteward(Steward steward) {
        stewardDao.updateSteward(steward);
    }

    @Override
    public void deleteSteward(Long id) {
        Steward toDelete = stewardDao.findById(id);
        stewardDao.deleteSteward(toDelete);
    }

    @Override
    public Steward findById(Long id) {
        return stewardDao.findById(id);
    }

    @Override
    public List<Steward> findAll() {
        return stewardDao.findAll();
    }

    @Override
    public Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        List<Steward> allStewards = stewardDao.findAll();
        Optional<Steward> result = allStewards.stream()
                .filter(
                        steward -> steward.getFlights()
                                .stream().anyMatch(flight -> flight.getDeparture().compareTo(from) < 0 && flight.getArrival().compareTo(from) < 0 ||
                                        flight.getDeparture().compareTo(to) > 0)
                ).findFirst();
        return result.orElse(null);
    }
}