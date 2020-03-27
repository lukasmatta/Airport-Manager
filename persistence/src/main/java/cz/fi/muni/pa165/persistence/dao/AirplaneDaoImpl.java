package cz.fi.muni.pa165.persistence.dao;

import cz.fi.muni.pa165.persistence.entity.Airplane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public class AirplaneDaoImpl implements AirplaneDao{

    /**
     * Implementation of AirplaneDao interface
     * @author Tomáš Janíček
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Airplane findFreePlaneInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        return null;
    }

    @Override
    public Airplane findById(Long id) {
        return null;
    }

    @Override
    public Collection<Airplane> findAll() {
        return null;
    }

    @Override
    public void insertAirplane(Airplane airplane) {

    }

    @Override
    public void updateAirplane(Airplane airplane) {

    }

    @Override
    public void deleteAirplane(Airplane airplane) {

    }
}
