package cz.muni.fi.pa165.airportmanager.dao;

import cz.muni.fi.pa165.airportmanager.entity.Steward;

import java.time.ZonedDateTime;
import java.util.List;

public interface StewardDao {

    Long insertSteward(Steward steward);
    void updateSteward(Steward steward);
    void deleteSteward(Steward steward);

    Steward findById(Long id);
    List<Steward> findAll();

    Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to);

}
