package cz.muni.fi.pa165.airportmanager.facade;

import cz.muni.fi.pa165.airportmanager.BeanMappingService;
import cz.muni.fi.pa165.airportmanager.StewardService;
import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.entity.Steward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Facade Implementation for Steward
 *
 * @author Almas Shakirtkhanov
 */

@Service
@Transactional
public class StewardFacadeImpl implements StewardFacade{
    @Autowired
    private StewardService stewardService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public StewardDTO findById(Long id) {
        Steward steward = stewardService.findById(id);
        return (steward == null) ? null : beanMappingService.mapTo(steward, StewardDTO.class);
    }

    @Override
    public Long insertSteward(StewardDTO steward) {
        Steward newSteward  = beanMappingService.mapTo(steward, Steward.class);
        return stewardService.insertSteward(newSteward);
    }

    @Override
    public void deleteSteward(Long id) {
       stewardService.deleteSteward(id);
    }

    @Override
    public void updateSteward(StewardDTO steward) {
        Steward newSteward = beanMappingService.mapTo(steward, Steward.class);
        stewardService.updateSteward(newSteward);
    }

    @Override
    public List<StewardDTO> findAll() {
        Collection<Steward> stewards = stewardService.findAll();
        return beanMappingService.mapToList(stewards, StewardDTO.class);
    }

    @Override
    public StewardDTO findFreeStewardInTimeInterval(ZonedDateTime from, ZonedDateTime to) {
        Steward newSteward = stewardService.findFreeStewardInTimeInterval(from,to);
        return beanMappingService.mapTo(newSteward,StewardDTO.class);
    }
}