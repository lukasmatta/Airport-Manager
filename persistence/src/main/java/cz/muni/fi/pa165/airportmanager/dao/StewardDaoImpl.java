package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class StewardDaoImpl implements StewardDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        return null;
    }


    @Override
    public List<Steward> findAll() {
        return em.createQuery(
                "SELECT steward FROM Steward steward", Steward.class
        ).getResultList();
    }

    @Override
    public void insertSteward(Steward steward) {
        em.persist(steward);
    }

    @Override
    public void updateSteward(Steward steward) {
        em.merge(steward);
    }

    @Override
    public void deleteSteward(Steward steward) {
        em.remove(steward);
    }

    @Override
    public Steward findById(Integer id) {
        return em.createQuery("select steward from Steward steward where u.id = :id", Steward.class)
                .setParameter("id", id).getSingleResult();
    }
}

