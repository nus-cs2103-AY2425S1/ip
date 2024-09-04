package easton.exception;

/**
 * Exception for an empty description.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Construct a new exception with a generated detail message.
     */
    public EmptyDescriptionException() {
        super("The description cannot be empty!");
    }
}
