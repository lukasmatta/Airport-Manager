package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Flight facade layer
 *
 * @author Petr Kantek
 */
@Service
public class FlightFacadeImpl implements FlightFacade {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    @Transactional
    public Long create(FlightDTO entity) {
        return null;
    }

    @Override
    @Transactional
    public void delete(FlightDTO entity) {

    }

    @Override
    @Transactional
    public void deleteById(long entityId) {

    }

    @Override
    @Transactional
    public FlightDTO update(FlightDTO entity) {
        return null;
    }

    @Override
    public FlightDTO findById(long id) {
        return null;
    }

    @Override
    public List<FlightDTO> findAll() {
        return null;
    }
}
