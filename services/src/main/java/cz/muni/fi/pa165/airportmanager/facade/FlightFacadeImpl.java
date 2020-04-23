package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
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
        Flight flight = beanMappingService.mapTo(entity, Flight.class);
        flightService.create(flight);
        return flight.getId();
    }

    @Override
    @Transactional
    public void delete(FlightDTO entity) {
        Flight flight = beanMappingService.mapTo(entity, Flight.class);
        flightService.delete(flight);
    }

    @Override
    @Transactional
    public void deleteById(long entityId) {
        flightService.deleteById(entityId);
    }

    @Override
    @Transactional
    public void update(FlightDTO entity) {
        Flight flight = beanMappingService.mapTo(entity, Flight.class);
        flightService.update(flight);
    }

    @Override
    public FlightDTO findById(long id) {
        Flight flight = flightService.findById(id);
        return (flight == null) ? null : beanMappingService.mapTo(flight, FlightDTO.class);
    }

    @Override
    public List<FlightDTO> findAll() {
        Collection<Flight> flights = flightService.findAll();
        return beanMappingService.mapToList(flights, FlightDTO.class);
    }
}
