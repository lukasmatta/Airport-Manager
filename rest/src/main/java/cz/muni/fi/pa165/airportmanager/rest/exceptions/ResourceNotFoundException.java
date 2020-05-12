package cz.muni.fi.pa165.airportmanager.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when any resource(entity) is not currently present or available
 *
 * @author Petr Kantek
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND) //reason="The resource you have requested does not exist")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
