package cz.muni.fi.pa165.airportmanager.rest.controllers;


import cz.muni.fi.pa165.airportmanager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.facade.AirplaneFacade;
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
 * Airplane HATEOAS-compliant controller
 *
 * @author Almas Shakirtkhanov
 */
@RestController
@RequestMapping(URIs.URI_AIRPLANES)
public class AirplaneController {
    final static Logger logger = LoggerFactory.getLogger(AirplaneController.class);

    @Autowired
    private AirplaneFacade airplaneFacade;

    @Autowired
    private GenericResourceAssembler<AirplaneDTO> airplaneResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CollectionModel<EntityModel<AirplaneDTO>>> getAirplanes() throws ResourceNotFoundException {
        logger.debug("rest Airplanes");
        return new ResponseEntity<>(airplaneResourceAssembler.toCollectionModel(airplaneFacade.findAllAirplanes(), this.getClass()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<AirplaneDTO>> getAirplane(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getAirplane");
        try {
            AirplaneDTO found =  airplaneFacade.findById(id);
            if (found == null) {
                throw new ResourceNotFoundException("Airplane with ID " + id + " does not exist in the database");
            }
            return new ResponseEntity<>(airplaneResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Airplane with ID " + id + " does not exist in the database");
        }
    }

    @RequestMapping(value = "/auth/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<AirplaneDTO>> createAirplane(@RequestBody AirplaneCreateDTO airplane) throws ResourceNotCreatedException {
        logger.debug("rest createAirplane()");
        try {
            Long id = airplaneFacade.createAirplane(airplane);
            AirplaneDTO created = airplaneFacade.findById(id);
            return new ResponseEntity<>(airplaneResourceAssembler.toModel(created, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotCreatedException("Airplane " + airplane.toString() + " was not created due to an illegal operation");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<AirplaneDTO>> updateAirplane(@RequestBody AirplaneDTO airplane) throws ResourceNotModifiedException {
        logger.debug("rest updateAirplane()");
        try {
            airplaneFacade.updateAirplane(airplane);
            AirplaneDTO update = airplaneFacade.findById(airplane.getId());
            return new ResponseEntity<>(airplaneResourceAssembler.toModel(update, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotModifiedException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteAirplane(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteAirplane()");
        try {
            airplaneFacade.deleteAirplane(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Airplane with ID: " + id + " could not be found in the database");
        }
    }

    @RequestMapping(value = "/freeAirplane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<AirplaneDTO>> getFreeSteward(@PathVariable("from") ZonedDateTime from, @PathVariable("to") ZonedDateTime to) throws ResourceNotFoundException {
        logger.debug("rest getFreeAirplane()");
        try {
            AirplaneDTO found = airplaneFacade.findFreePlaneInTimeInterval(from,to);
            if (found == null) {
                throw new ResourceNotFoundException("Cannot find free Airplane in this period of time");
            }
            return new ResponseEntity<>(airplaneResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot find free Airplane in this period of time");
        }
    }
}