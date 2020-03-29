package dao;

import entity.Steward;

import java.time.ZonedDateTime;
import java.util.List;

public interface DaoSteward {

    void insertSteward(Steward steward);
    void updateSteward(Steward steward);
    void deleteSteward(Steward steward);

    Steward findById(Integer id);
    List<Steward> findAll();

    Steward findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to);

}
