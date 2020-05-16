package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirplaneService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Facade Implementation for Airplane
 *
 * @author Lukáš Matta
 */

@Service
@Transactional
public class AirplaneFacadeImpl implements AirplaneFacade {
    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public AirplaneDTO findById(Long id) {
        Airplane airplane = airplaneService.findById(id);
        return (airplane == null) ? null : beanMappingService.mapTo(airplane, AirplaneDTO.class);
    }

    @Override
    public void deleteAirplane(Long id) {
        airplaneService.deleteAirplane(id);
    }

    @Override
    public Long createAirplane(AirplaneCreateDTO airplane) {
        Airplane newAirport = beanMappingService.mapTo(airplane, Airplane.class);
        airplaneService.createAirplane(newAirport);
        return newAirport.getId();
    }

    @Override
    public void updateAirplane(AirplaneDTO airplane) {
        Airplane newAirport = beanMappingService.mapTo(airplane, Airplane.class);
        airplaneService.updateAirplane(newAirport);
    }

    @Override
    public List<AirplaneDTO> findAllAirplanes() {
        Collection<Airplane> airplanes = airplaneService.findAllAirplanes();
        return beanMappingService.mapToList(airplanes, AirplaneDTO.class);
    }

    @Override
    public AirplaneDTO findFreePlaneInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        Airplane airplane = airplaneService.findFreePlaneInTimeInterval(from, to);
        return (airplane == null) ? null : beanMappingService.mapTo(airplane, AirplaneDTO.class);
    }
}
