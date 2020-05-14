package cz.muni.fi.pa165.airportmanager.sampledata;

import cz.muni.fi.pa165.airportmanager.AirportService;
import cz.muni.fi.pa165.airportmanager.UserService;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Inject
    private UserService userService;

    @Inject
    private AirportService airportService;


    @Override
    public void loadData() {
        log.info("Add admin");
        User admin = new User();
        admin.setName("admin");
        admin.setAdmin(true);
        userService.register(admin, "admin");

        Airport airport = new Airport();
        airport.setCity("Gbelany");
        airport.setCountry("Slovakia");
        airportService.createAirport(airport);
    }
}
