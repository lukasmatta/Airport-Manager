package cz.muni.fi.pa165.airportmanager.exceptions;

/**
 * Exception thrown when a plane has a scheduled flight intersecting with the one it is being assigned to.
 *
 * @author Petr Kantek
 */
public class PlaneNotAvailableException extends RuntimeException {

    public PlaneNotAvailableException(String message) {
        super(message);
    }
}
