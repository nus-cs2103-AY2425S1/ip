package dudu.exception;

/**
 * Represents a custom checked exception where the user fails to input a description for a task.
 */
public class MissingDescriptionException extends Exception {
    /**
     * Constructs an MissingDescriptionException.
     *
     * @param message Error message.
     */
    public MissingDescriptionException(String message) {
        super(message);
    }
}
