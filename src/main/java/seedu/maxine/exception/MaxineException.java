package seedu.maxine.exception;

/**
 * Custom exception class for the Maxine application.
 * This exception is thrown to indicate specific error conditions
 * related to the application.
 */
public class MaxineException extends Exception {
    /**
     * Constructs a new MaxineException with the specified detail message.
     * <p>
     * This constructor creates a MaxineException with the specified message which can
     * be used to provide more context about the error that occurred.
     * </p>
     *
     * @param message The detail message that provides more information about the exception.
     */
    public MaxineException(String message) {
        super(message);
    }
}
