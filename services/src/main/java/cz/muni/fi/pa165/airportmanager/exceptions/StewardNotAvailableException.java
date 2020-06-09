package cz.muni.fi.pa165.airportmanager.exceptions;

/**
 * Exception thrown when a steward has an already scheduled flight intersecting with the one he is being
 * assigned to.
 *
 * @author Petr Kantek
 */
public class StewardNotAvailableException extends RuntimeException {

    public StewardNotAvailableException(String message) {
        super(message);
    }
}
