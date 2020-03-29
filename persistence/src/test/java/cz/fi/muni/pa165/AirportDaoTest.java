package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.dao.AirportDAO;
import cz.muni.fi.pa165.airportmanager.dao.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AirportDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public AirportDAO airportDAO;

    @Test
    public void find() {
        Airport airport = new Airport();
        airport.setCity("New York");


    }

}
