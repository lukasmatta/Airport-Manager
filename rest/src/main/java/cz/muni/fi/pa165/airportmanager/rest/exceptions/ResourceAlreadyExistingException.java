package cz.muni.fi.pa165.airportmanager.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when user tried to create an already existing resource
 * (entity with the same ID)
 *
 * @author Petr Kantek
 */
@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource was not added")
public class ResourceAlreadyExistingException extends RuntimeException {
}
