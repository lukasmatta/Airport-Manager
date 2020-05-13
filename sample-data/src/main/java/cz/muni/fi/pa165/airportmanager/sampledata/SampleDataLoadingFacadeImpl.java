package cz.muni.fi.pa165.airportmanager.sampledata;

import cz.muni.fi.pa165.airportmanager.UserService;
import cz.muni.fi.pa165.airportmanager.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private UserService userService;
    @Autowired


    @Override
    public void loadData() {
        log.info("Add admin");
        User admin = new User();
        admin.setName("admin");
        admin.setAdmin(true);
        userService.registerUser(admin, "password");
    }
}
