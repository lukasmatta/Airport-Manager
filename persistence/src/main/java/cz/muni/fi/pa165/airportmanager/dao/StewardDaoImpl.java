package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Flight;
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
    public List<Steward> findAll() {
        return em.createQuery(
                "SELECT steward FROM Steward steward", Steward.class
        ).getResultList();
    }

    /**
     * TODO: Implement this in the next milestone
     * @param from
     * @param to
     * @return
     */
    @Override
    public Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        return null;
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
        return em.find(Steward.class, id);
    }
}