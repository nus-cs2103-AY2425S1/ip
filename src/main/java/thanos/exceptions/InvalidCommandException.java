package thanos.exceptions;

/**
 * Exception thrown to indicate that a command is invalid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an {@code InvalidCommandException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
