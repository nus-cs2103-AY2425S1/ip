package elysia.exceptions;

/**
 * Custom exception class for handling errors specific to the Elysia application.
 * Extends the {@code Exception} class and provides a mechanism to display custom error messages.
 */
public class ElysiaException extends Exception {

    /**
     * Constructs a new {@code ElysiaException} with the specified detail message.
     * The message can provide additional context about the error encountered in the application.
     *
     * @param message the detail message that describes the exception.
     */
    public ElysiaException(String message) {
        super(message);
    }
}
