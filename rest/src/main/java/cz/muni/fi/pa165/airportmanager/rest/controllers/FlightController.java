package cz.muni.fi.pa165.airportmanager.rest.controllers;

import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.facade.FlightFacade;
import cz.muni.fi.pa165.airportmanager.rest.URIs;
import cz.muni.fi.pa165.airportmanager.rest.assemblers.GenericResourceAssembler;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceAlreadyExistingException;
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

/**
 * Flight HATEOAS-compliant controller
 *
 * @author
 */
@RestController
@RequestMapping(URIs.URI_FLIGHTS)
public class FlightController {
    final static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightFacade flightFacade;

    @Autowired
    private GenericResourceAssembler<FlightDTO> flightResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CollectionModel<EntityModel<FlightDTO>>> getAirplanes() throws ResourceNotFoundException {
        logger.debug("rest Airplanes");
        return new ResponseEntity<>(flightResourceAssembler.toCollectionModel(flightFacade.findAll(), this.getClass()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<FlightDTO>> getFlight(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getFlight");
        try {
            FlightDTO found =  flightFacade.findById(id);
            if (found == null) {
                throw new ResourceNotFoundException("Flight with ID " + id + " does not exist in the database");
            }
            return new ResponseEntity<>(flightResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Flight with ID " + id + " does not exist in the database");
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<FlightDTO>> createFlight(@RequestBody FlightDTO flight) throws Exception {
        logger.debug("rest createFlight()");
        try {
            if (flightFacade.findById(flight.getId()) != null) {
                throw new ResourceAlreadyExistingException("Flight " + flight.toString() + " already exists in the database");
            }
            flightFacade.create(flight);
            FlightDTO created = flightFacade.findById(flight.getId());
            return new ResponseEntity<>(flightResourceAssembler.toModel(created, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException("Flight " + flight.toString() + " already exists in the database");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<FlightDTO>> updateFlight(@RequestBody FlightDTO flight) throws ResourceNotModifiedException {
        logger.debug("rest updateFlight()");
        try {
            flightFacade.update(flight);
            FlightDTO update = flightFacade.findById(flight.getId());
            return new ResponseEntity<>(flightResourceAssembler.toModel(update, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotModifiedException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteFlightByID(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteFlight()");
        try {
            flightFacade.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Flight with ID: " + id + " could not be found in the database");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteFlight(@RequestBody FlightDTO flight) throws ResourceNotModifiedException {
        logger.debug("rest deleteFlight()");
        try {
            flightFacade.delete(flight);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Flight could not be found in the database");
        }
    }
}