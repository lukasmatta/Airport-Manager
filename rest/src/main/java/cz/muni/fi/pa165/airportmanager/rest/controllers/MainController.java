package cz.muni.fi.pa165.airportmanager.rest.controllers;

import cz.muni.fi.pa165.airportmanager.rest.URIs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The main rest controller
 *
 * @author Petr Kantek
 */
@RestController
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * The main entry point of the REST API
     *
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();

        resourcesMap.put("stewards_uri", URIs.URI_STEWARDS);
        resourcesMap.put("flight_uri", URIs.URI_FLIGHTS);
        resourcesMap.put("airplanes_uri", URIs.URI_AIRPLANES);
        resourcesMap.put("airports_uri", URIs.URI_AIRPORTS);

        return Collections.unmodifiableMap(resourcesMap);

    }
}
