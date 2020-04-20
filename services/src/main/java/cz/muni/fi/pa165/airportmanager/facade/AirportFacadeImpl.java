package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.AirportService;
import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.dto.AirportDto;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Facade Implementation for Airport
 *
 * @author Tomáš Janíček
 */

@Service
@Transactional
public class AirportFacadeImpl implements AirportFacade {
    @Autowired
    private AirportService airportService;

    @Autowired
    private BeanMappingService beanMappingService;


    @Override
    public AirportDto findById(Long id) {
        Airport airport = airportService.findById(id);
        return (airport == null) ? null : beanMappingService.mapTo(airport, AirportDto.class);
    }

    @Override
    public void deleteAirport(Long id) {
        airportService.deleteAirport(id);
    }

    @Override
    public void createAirport(AirportDto airport) {
        Airport newAirport = beanMappingService.mapTo(airport, Airport.class);
        airportService.createAirport(newAirport);
    }

    @Override
    public void updateAirport(AirportDto airport) {
        Airport newAirport = beanMappingService.mapTo(airport, Airport.class);
        airportService.updateAirport(newAirport);
    }

    @Override
    public List<AirportDto> findAllAirports() {
        List<Airport> airports = airportService.findAllAirports();
        return beanMappingService.mapToList(airports, AirportDto.class);
    }
}
