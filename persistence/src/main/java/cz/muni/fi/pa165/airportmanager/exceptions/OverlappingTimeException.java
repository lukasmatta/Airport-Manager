package cz.muni.fi.pa165.airportmanager.exceptions;

/**
 * @author Petr Kantek
 */
public class OverlappingTimeException extends Exception {

    public OverlappingTimeException() {
        super();
    }

    public OverlappingTimeException(String message) {
        super(message);
    }

    public OverlappingTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverlappingTimeException(Throwable cause) {
        super(cause);
    }
}
