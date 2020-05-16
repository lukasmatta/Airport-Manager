package cz.muni.fi.pa165.airportmanager.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an instance of CreateDTO is illegal or it already exists in the database.
 *
 * @author Petr Kantek
 */
@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceNotCreatedException extends RuntimeException {

    public ResourceNotCreatedException(String message) {
        super(message);
    }
}
