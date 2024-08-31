package seedu.maxine.exception;

public class MaxineException extends Exception {

    /**
     * Default constructor for MaxineException.
     * <p>
     * This constructor creates a MaxineException with no specific detail message.
     * </p>
     */
    public MaxineException() {
        // nothing
    }

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
