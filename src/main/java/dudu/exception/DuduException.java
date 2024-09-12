package dudu.exception;

/**
 * Represents general exception from Dudu application that does not fit the other exceptions
 */
public class DuduException extends Exception {
    public DuduException(String message) {
        super(message);
    }
}
