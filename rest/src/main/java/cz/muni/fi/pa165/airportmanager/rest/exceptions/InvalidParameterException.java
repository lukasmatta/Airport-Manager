package cz.muni.fi.pa165.airportmanager.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a parameter is illegal
 *
 * @author Petr Kantek
 */
@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason = "At least one of the parameters is illegal")
public class InvalidParameterException extends RuntimeException {
}
