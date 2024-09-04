package dudu.exception;

/**
 * Represents a custom checked exception where the user fails to input a description for a task
 */
public class MissingDescriptionException extends Exception {
    /**
     * Constructs an MissingDescriptionException with the specified detail message.
     *
     * @param message The detail message which explains the reason for the exception.
     */
    public MissingDescriptionException(String message) {
        super(message);
    }
}
