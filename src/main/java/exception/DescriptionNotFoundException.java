package exception;

/**
 * Exception thrown when a task description is not found or is empty.
 */
public class DescriptionNotFoundException extends Exception {

    /**
     * Constructs a new DescriptionNotFoundException with no detail message.
     */
    public DescriptionNotFoundException() {
        super();
    }

    /**
     * Constructs a new DescriptionNotFoundException with the specified detail message.
     *
     * @param message The detail message to be used for this exception.
     */
    public DescriptionNotFoundException(String message) {
        super(message);
    }
}