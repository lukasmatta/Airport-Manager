package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Airport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AirportDAOImpl implements AirportDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long insertAirport(Airport airport) {
        em.persist(airport);
        return airport.getId();
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return em.merge(airport);
    }

    @Override
    public void deleteAirport(Airport airport) {
        em.remove(airport);
    }

    @Override
    public Airport findById(Long id) {
        return em.find(Airport.class, id);
    }

    @Override
    public List<Airport> findAll() {
        return em.createQuery(
                "SELECT airport FROM Airport airport", Airport.class
        ).getResultList();
    }
}
