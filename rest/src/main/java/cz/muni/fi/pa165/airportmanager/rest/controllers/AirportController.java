package cz.muni.fi.pa165.airportmanager.rest.controllers;

import cz.muni.fi.pa165.airportmanager.dto.AirportDTO;
import cz.muni.fi.pa165.airportmanager.facade.AirportFacade;
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
 * Airport HATEOAS-compliant controller
 *
 * @author Petr Kantek
 */
@RestController
@RequestMapping(URIs.URI_AIRPORTS)
public class AirportController {

    final static Logger logger = LoggerFactory.getLogger(AirportController.class);

    @Autowired
    private AirportFacade airportFacade;

    @Autowired
    private GenericResourceAssembler<AirportDTO> airportResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CollectionModel<EntityModel<AirportDTO>>> getAirports() throws ResourceNotFoundException {
        logger.debug("rest getAirports");
        return new ResponseEntity<>(airportResourceAssembler.toCollectionModel(airportFacade.findAllAirports(), this.getClass()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<AirportDTO>> getAirport(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getAirport");
        try {
            AirportDTO found =  airportFacade.findById(id);
            if (found == null) {
                throw new ResourceNotFoundException("Airport with ID " + id + " does not exist in the database");
            }
            return new ResponseEntity<>(airportResourceAssembler.toModel(found, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
                throw new ResourceNotFoundException("Airport with ID " + id + " does not exist in the database");
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<AirportDTO>> createAirport(@RequestBody AirportDTO airport) throws Exception {
        logger.debug("rest createAirport()");
        try {
            if (airportFacade.findById(airport.getId()) != null) {
                throw new ResourceAlreadyExistingException("Airport " + airport.toString() + " already exists in the database");
            }
            airportFacade.createAirport(airport);
            AirportDTO created = airportFacade.findById(airport.getId());
            return new ResponseEntity<>(airportResourceAssembler.toModel(created, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException("Airport " + airport.toString() + " already exists in the database");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<AirportDTO>> updateAirport(@RequestBody AirportDTO airport) throws ResourceNotModifiedException {
        logger.debug("rest updateAirport()");
        try {
            airportFacade.updateAirport(airport);
            AirportDTO update = airportFacade.findById(airport.getId());
            return new ResponseEntity<>(airportResourceAssembler.toModel(update, this.getClass()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotModifiedException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteAirportById(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteAirportById()");
        try {
            airportFacade.deleteAirport(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Airport with ID: " + id + " could not be found in the database");
        }
    }
}
