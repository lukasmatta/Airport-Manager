package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirplaneService;
import cz.muni.fi.pa165.airportmanager.AirportService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightCreateDTO;
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
@Transactional
public class FlightFacadeImpl implements FlightFacade {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(FlightCreateDTO entity) {
        Flight flight = beanMappingService.mapTo(entity, Flight.class);

        flight.setArrival(entity.getArrival());
        flight.setDeparture(entity.getDeparture());

        flight.setOrigin(airportService.findById(entity.getOriginID()));
        flight.setDestination(airportService.findById(entity.getDestinationID()));

        flight.setPlane(airplaneService.findById(entity.getPlaneID()));


        flightService.create(flight);
        return flight.getId();
    }

    @Override
    public void delete(FlightDTO entity) {
        Flight flight = beanMappingService.mapTo(entity, Flight.class);
        flightService.delete(flight);
    }

    @Override
    public void deleteById(long entityId) {
        flightService.deleteById(entityId);
    }

    @Override
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
