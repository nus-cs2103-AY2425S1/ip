package tick.exceptions;

/**
 * TickException is a custom exception class that is thrown when an error occurs in the Tick class.
 */
public class TickException extends Exception {
    /**
     * Constructor for TickException.
     *
     * @param message Error message.
     */
    public TickException(String message) {
        super(message);
    }
}
