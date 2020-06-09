package cz.muni.fi.pa165.airportmanager.exceptions;

/**
 * An exception thrown when trying to assign a nonexistent steward to a flight.
 *
 * @author Petr Kantek
 */
public class NonExistentAssignedStewardException extends RuntimeException {

    public NonExistentAssignedStewardException(String message) {
        super(message);
    }
}
