package cz.muni.fi.pa165.airportmanager;

import cz.muni.fi.pa165.airportmanager.dao.FlightDao;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Flight service layer
 *
 * @author Petr Kantek
 */
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;

    @Override
    @Transactional
    public Long create(Flight entity) {
        return flightDao.create(entity);
    }

    @Override
    @Transactional
    public void delete(Flight entity) {
        flightDao.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(long entityId) {
        flightDao.deleteById(entityId);
    }

    @Override
    @Transactional
    public void update(Flight entity) {
        flightDao.update(entity);
    }

    @Override
    public Flight findById(long id) {
        return flightDao.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightDao.findAll();
    }
}
