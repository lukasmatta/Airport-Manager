package cz.muni.fi.pa165.airportmanager.exceptions;

/**
 * Exception thrown when a nonexistent plane is assigned to a flight.
 *
 * @author Petr Kantek
 */
public class NonExistentAssignedPlaneException extends RuntimeException{

    public NonExistentAssignedPlaneException(String message) {
        super(message);
    }
}
