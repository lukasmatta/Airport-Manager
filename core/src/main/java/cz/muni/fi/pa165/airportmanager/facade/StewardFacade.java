package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface StewardFacade {
    /**
     * Creates new steward
     * @param steward
     * @return
     */
    Long insertSteward(StewardCreateDTO steward);


    /**
     * Updates existing steward
     * @param steward
     * @return
     */
    void updateSteward(StewardDTO steward);

    /**
     * Removes existing steward
     * @param id of Steward
     */
    void deleteSteward(Long id);

    /**
     * Find steward by it's ID
     * @param id
     * @return
     */
    StewardDTO findById(Long id);

    /**
     * Returns list of all existing stewards
     * @return
     */
    List<StewardDTO> findAll();
    /**
     * Returns steward who is available for the flight
     * @return entity Steward
     */
    StewardDTO findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to);

}