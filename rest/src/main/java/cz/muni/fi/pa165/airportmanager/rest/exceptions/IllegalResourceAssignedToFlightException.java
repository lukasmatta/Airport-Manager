package cz.muni.fi.pa165.airportmanager.rest.exceptions;

/**
 * Exception thrown when steward or plane assigned to a flight is either nonexistent or unavailable
 *
 * @author Petr Kantek
 */
public class IllegalResourceAssignedToFlightException extends RuntimeException{

    public IllegalResourceAssignedToFlightException(String message) {
        super(message);
    }
}
