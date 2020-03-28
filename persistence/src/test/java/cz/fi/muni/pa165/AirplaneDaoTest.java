package cz.fi.muni.pa165;

import cz.muni.fi.pa165.airportmanager.dao.AirplaneDao;
import cz.muni.fi.pa165.airportmanager.dao.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AirplaneDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public AirplaneDao airplaneDao;

    @Test
    public void find() {
        Airplane airplane = new Airplane();
        airplane.setName("CoolOne");

        airplaneDao.insertAirplane(airplane);

    }

}
