package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.*;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import cz.muni.fi.pa165.airportmanager.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private StewardService  stewardService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(FlightCreateDTO entity) {
        Flight flight = beanMappingService.mapTo(entity, Flight.class);

        flight.setArrival(ZonedDateTime.parse(entity.getArrival(), DateTimeFormatter.RFC_1123_DATE_TIME));
        flight.setDeparture(ZonedDateTime.parse(entity.getDeparture(), DateTimeFormatter.RFC_1123_DATE_TIME));

        Long[] stewardsList = entity.getStewardsList();

        if (stewardsList != null && stewardsList.length > 0) {
            Steward currentSteward;
            for (long id : stewardsList) {
                currentSteward = stewardService.findById(id);
                if (currentSteward == null) {
                    throw new NonExistentAssignedStewardException("Steward with ID: " + id + " does not exist");
                }
                try {
                    flight.addSteward(currentSteward);
                } catch (OverlappingTimeException e) {
                    throw new StewardNotAvailableException("Steward with ID: " + currentSteward.getId() + e.getMessage());
                }
            }
        }

        flight.setOrigin(airportService.findById(entity.getOriginID()));
        flight.setDestination(airportService.findById(entity.getDestinationID()));

        Airplane plane = airplaneService.findById(entity.getPlaneID());
        if (plane == null) {
            throw new NonExistentAssignedPlaneException("Plane with ID: " + entity.getPlaneID() + " does not exist");
        }

        try {
            flight.setPlane(plane);
        } catch (OverlappingTimeException e) {
            throw new PlaneNotAvailableException("Plane with ID: " + entity.getPlaneID() + e.getMessage());
        }

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
