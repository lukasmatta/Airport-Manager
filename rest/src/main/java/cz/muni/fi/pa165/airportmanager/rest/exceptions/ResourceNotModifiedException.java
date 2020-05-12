package cz.muni.fi.pa165.airportmanager.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown whenever modification of some resource(entity) fails for some reason
 *
 * @author Petr Kantek
 */
@ResponseStatus(value= HttpStatus.NOT_MODIFIED, reason = "The resource has not been modified")
public class ResourceNotModifiedException extends RuntimeException {
}
