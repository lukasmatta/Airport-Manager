package cz.muni.fi.pa165.airportmanager.rest.controllers;

import java.util.List;

import cz.muni.fi.pa165.airportmanager.dto.FlightDTO;
import cz.muni.fi.pa165.airportmanager.facade.FlightFacade;
import cz.muni.fi.pa165.airportmanager.rest.URIs;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.ResourceNotModified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Rest controller for flights
 *
 * @author Petr Kantek
 */
@RestController
@RequestMapping(value = URIs.URI_FLIGHTS)
public class FlightController {

    final static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightFacade flightFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<FlightDTO> getFlights() {
        logger.debug("rest getFlights()");
        return flightFacade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final FlightDTO getFlight(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest getFlight()");
        try {
            return flightFacade.findById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteFlight(@RequestBody FlightDTO flight) throws ResourceNotFoundException {
        logger.debug("rest deleteFlight()");
        try {
            flightFacade.delete(flight);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteFlightById(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteFlightById()");
        try {
            flightFacade.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final FlightDTO createFlight(@RequestBody FlightDTO flight) throws ResourceAlreadyExistingException {
        logger.debug("rest createFlight()");
        try {
            flightFacade.create(flight);
            return flightFacade.findById(flight.getId());
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final FlightDTO updateFlight(@RequestBody FlightDTO flight) throws ResourceNotFoundException {
        logger.debug("rest updateFlight()");
        try {
            flightFacade.update(flight);
            return flightFacade.findById(flight.getId());
        } catch (Exception e) {
            throw new ResourceNotModified();
        }
    }
}
