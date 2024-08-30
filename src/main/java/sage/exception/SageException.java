package sage.exception;

/**
 * Represents a custom exception used in the Sage application
 */
public class SageException extends Exception {
    /**
     * Constructs a new SageException with the specified detail message
     *
     * @param message The detail message that explains the reason for the exception
     */
    public SageException(String message) {
        super(message);
    }
}
