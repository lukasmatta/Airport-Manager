package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class DaoStewardImpl implements DaoSteward {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        Steward s = new Steward();
        return null;
    }


    @Override
    public List<Steward> findAll() {
        return em.createQuery(
                "SELECT steward FROM Steward steward", Steward.class
        ).getResultList();
    }

    @Override
    public Long insertSteward(Steward steward) {
        em.persist(steward);
        return steward.getId();
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
    public Steward findById(Long id) {
        return em.createQuery("select steward from Steward steward where steward.id = :id", Steward.class)
                .setParameter("id", id).getSingleResult();
    }
}