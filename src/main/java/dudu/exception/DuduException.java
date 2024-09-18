package dudu.exception;

/**
 * Represents general exception from Dudu application.
 */
public class DuduException extends Exception {
    /**
     * Constructs a DuduException.
     *
     * @param message Error message.
     */
    public DuduException(String message) {
        super(message);
    }
}
