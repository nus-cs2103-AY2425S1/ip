package jade.exception;

/**
 * Custom exception class for handling Jade-specific errors.
 */
public class JadeException extends Exception {
    /**
     * Constructs a JadeException with the specified message.
     *
     * @param message The detail message for this exception.
     */
    public JadeException(String message) {
        super(message);
    }
}
