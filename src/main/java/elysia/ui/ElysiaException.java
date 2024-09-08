package elysia.ui;

/**
 * Represents a custom exception for the Elysia application.
 * Extends the Exception class and provides a way to signal errors specific to the application.
 */
public class ElysiaException extends Exception{
    /**
     * Constructs a new ElysiaException with the specified detail message.
     *
     * @param message The detail message that provides information about the exception.
     */
    public ElysiaException(String message) {
        super(message);
    }
}
