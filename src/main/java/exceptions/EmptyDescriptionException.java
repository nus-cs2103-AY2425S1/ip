package exceptions;

/**
 * Exception thrown when a required description is missing.
 * <p>
 * This exception is used to indicate that a description is needed but not provided for a task creation or similar operations.
 * </p>
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Constructs an {@code EmptyDescriptionException} with the specified detail message.
     *
     * @param message the detail message which is saved for later retrieval
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}