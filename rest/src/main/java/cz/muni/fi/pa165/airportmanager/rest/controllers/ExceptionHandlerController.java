package cz.muni.fi.pa165.airportmanager.rest.controllers;

import cz.muni.fi.pa165.airportmanager.entity.Flight;
import cz.muni.fi.pa165.airportmanager.rest.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handler for exceptions thrown on the REST api
 *
 * @author Petr Kantek
 */
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return getObjectResponseEntity(ex.getMessage());
    }


    @ExceptionHandler(ResourceNotCreatedException.class)
    public ResponseEntity<Object> handleResourceAlreadyExistingException(ResourceNotCreatedException ex) {
        return getObjectResponseEntity(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotModifiedException.class)
    public ResponseEntity<Object> handleResourceNotModifiedException(ResourceNotModifiedException ex) {
        return getObjectResponseEntity(ex.getMessage());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {
        return getObjectResponseEntity(ex.getMessage());
    }

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(IllegalResourceAssignedToFlightException.class)
    public ResponseEntity<Object> handleIllegalResourceAssignedToFlightException(IllegalResourceAssignedToFlightException ex) {
        return getObjectResponseEntity(ex.getMessage());
    }

    private ResponseEntity<Object> getObjectResponseEntity(String message) {
        Map<String, Object> exBody = new LinkedHashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        exBody.put("timestamp", dtf.format(LocalDateTime.now()));
        exBody.put("status", "FAIL");
        exBody.put("message", message);
        return new ResponseEntity<>(exBody, HttpStatus.NOT_FOUND);
    }
}
