package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface StewardFacade {
    /**
     * Creates new steward
     * @param steward
     * @return
     */
    Long insertSteward(StewardDTO steward);


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

}