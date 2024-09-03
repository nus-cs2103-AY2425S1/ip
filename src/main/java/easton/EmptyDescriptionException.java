package easton;

/**
 * Exception for an empty description.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Construct a new exception with a generated detail message.
     */
    EmptyDescriptionException() {
        super("The description cannot be empty!");
    }
}
