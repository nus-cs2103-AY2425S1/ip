package rudolf.exception;

/**
 * Represents an exception specific to the Rudolf application.
 * This exception is thrown when the Rudolf application encounters an error.
 */
public class RudolfException extends Exception {
    /**
     * Constructs a new RudolfException with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public RudolfException(String message) {
        super(message);
    }
}
