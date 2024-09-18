package exceptions;

/**
 * Exception thrown when no task description is provided.
 */
public class NoTaskDescriptionException extends Exception {

    /**
     * Constructs a new NoTaskDescriptionException with no detail message.
     */
    public NoTaskDescriptionException() {
        super();
    }
}
