package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Petr Kantek
 */
@Repository
public class FlightDaoImpl implements FlightDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Flight entity) {
        entityManager.persist(entity);
    }

    @Override
    public Flight update(Flight entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Flight entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(long entityId) {
        Flight entity = findById(entityId);
        delete(entity);
    }

    @Override
    public Flight findById(long id) {
        return entityManager.find(Flight.class, id);
    }

    @Override
    public List<Flight> findAll() {
        return entityManager.createQuery("from " + Flight.class.getName(), Flight.class).getResultList();
    }
}
