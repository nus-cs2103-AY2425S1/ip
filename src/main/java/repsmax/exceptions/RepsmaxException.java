package repsmax.exceptions;

/**
 * Represents a custom exception specific to the Repsmax application.
 * Extends the standard {@link Exception} class to handle application-specific errors.
 */
public class RepsmaxException extends Exception {

    /**
     * Constructs a new {@code RepsmaxException} with the specified detail message.
     * The message describes the specific error or exception that occurred within the Repsmax application.
     *
     * @param message The detail message that provides more information about the exception.
     *                This message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public RepsmaxException(String message) {
        super(message);
    }
}
