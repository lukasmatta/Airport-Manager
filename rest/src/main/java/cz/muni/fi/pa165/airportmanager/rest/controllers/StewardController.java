package cz.muni.fi.pa165.airportmanager.rest.controllers;

import cz.muni.fi.pa165.airportmanager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.StewardDTO;
import cz.muni.fi.pa165.airportmanager.facade.StewardFacade;
import cz.muni.fi.pa165.airportmanager.rest.URIs;
import cz.muni.fi.pa165.airportmanager.rest.assemblers.GenericResourceAssembler;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceNotCreatedException;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceNotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

/**
 * Steward HATEOAS-compliant controller
 *
 * @author Petr Kantek
 */
@RestController
@RequestMapping(URIs.URI_STEWARDS)
public class StewardController {

    final static Logger logger = LoggerFactory.getLogger(StewardController.class);

    @Autowired
    private StewardFacade stewardFacade;

    @Autowired
    private GenericResourceAssembler<StewardDTO> genericResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CollectionModel<EntityModel<StewardDTO>>> getStewards() throws ResourceNotFoundException {
        logger.debug("rest Stewards");
        return new ResponseEntity<>(genericResourceAssembler.toCollectionModel(stewardFacade.findAll(), this.getClass()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<StewardDTO>> getSteward(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getSteward");
        try {
            StewardDTO found =  stewardFacade.findById(id);
            if (found == null) {
                throw new ResourceNotFoundException("Steward with ID " + id + " does not exist in the database");
            }
            return new ResponseEntity<>(genericResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Steward with ID " + id + " does not exist in the database");
        }
    }

    @RequestMapping(value = "/auth/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<StewardDTO>> createSteward(@RequestBody StewardCreateDTO steward) throws ResourceNotCreatedException {
        logger.debug("rest createSteward()");
        try {
            Long id = stewardFacade.insertSteward(steward);
            return new ResponseEntity<>(genericResourceAssembler.toModel(stewardFacade.findById(id), this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotCreatedException("Steward " + steward.toString() + " was not created due to an illegal operation");
        }
    }

    @RequestMapping(value = "/auth/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<StewardDTO>> updateSteward(@RequestBody StewardDTO steward) throws ResourceNotModifiedException {
        logger.debug("rest updateSteward()");
        try {
            stewardFacade.updateSteward(steward);
            StewardDTO update = stewardFacade.findById(steward.getId());
            return new ResponseEntity<>(genericResourceAssembler.toModel(update, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotModifiedException();
        }
    }

    @RequestMapping(value = "/auth/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteStewardById(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteStewardById()");
        try {
            stewardFacade.deleteSteward(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Steward with ID: " + id + " could not be found in the database");
        }
    }

    @RequestMapping(value = "/freeSteward", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<StewardDTO>> getFreeSteward(@PathVariable("from") ZonedDateTime from, @PathVariable("to") ZonedDateTime to) throws ResourceNotFoundException {
        logger.debug("rest getFreeSteward()");
        try {
            StewardDTO found =  stewardFacade.findFreeStewardInTimeInterval(from,to);
            if (found == null) {
                throw new ResourceNotFoundException("Cannot find free Steward in this period of time");
            }
            return new ResponseEntity<>(genericResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot find free Steward in this period of time");
        }
    }


}
