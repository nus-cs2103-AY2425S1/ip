package easton.exception;

/**
 * Exception for an empty description.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Constructs a new exception with a generated detail message.
     */
    public EmptyDescriptionException() {
        super("The description cannot be empty!");
    }
}
