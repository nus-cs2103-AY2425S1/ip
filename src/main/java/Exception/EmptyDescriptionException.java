package Exception;

/**
 * Exception thrown when a task description is expected but not provided or found to be empty.
 * This custom exception is used to handle specific validation scenarios in the application where
 * task descriptions are mandatory for processing.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs a new EmptyDescriptionException with a detailed message.
     * This constructor is typically called when a task operation is attempted without a required description.
     *
     * @param message The detailed message that explains the cause of the exception.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
