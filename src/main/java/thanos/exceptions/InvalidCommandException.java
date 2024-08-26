package thanos.exceptions;

/**
 * Exception thrown to indicate that a command is invalid.
 * <p>
 * The {@code InvalidCommandException} class extends {@code Exception} and is used to signal issues with
 * commands that are not formatted correctly or contain errors. It provides a custom error message to indicate
 * the nature of the problem.
 * </p>
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an {@code InvalidCommandException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception. This message provides information
     *                about why the command is considered invalid and can be used for debugging or user feedback.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
