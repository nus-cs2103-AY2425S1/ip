package peppa.data;

/**
 * Exception thrown by the Peppa application.
 */
public class PeppaException extends Exception {
    /**
     * Returns an exception with a message.
     *
     * @param message The message for the exception.
     * @return The exception.
     */
    public PeppaException(String message) {
        super(message);
    }
}
