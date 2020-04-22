package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.StewardDao;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service for Steward
 *
 * @author Almas Shakirtkhanov
 */

@Service
public class StewardServiceImpl implements StewardService {

    @Autowired
    private StewardDao stewardDao;

    @Override
    @Transactional
    public Long insertSteward(Steward entity) {
        return stewardDao.insertSteward(entity);
    }

    @Override
    @Transactional
    public void updateSteward(Steward steward) {
        stewardDao.updateSteward(steward);
    }

    @Override
    @Transactional
    public void deleteSteward(Long id) {
        Steward toDelete = stewardDao.findById(id);
        stewardDao.deleteSteward(toDelete);
    }

    @Override
    @Transactional
    public Steward findById(Long id) {
        return stewardDao.findById(id);
    }

    @Override
    @Transactional
    public List<Steward> findAll() {
        return stewardDao.findAll();
    }

}