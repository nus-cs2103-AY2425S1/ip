package max.exception;

/**
 * Represents an exception specific to the Max application.
 * This class extends the built-in {@code Exception} class and allows for custom exception handling
 * within the Max application.
 */
public class MaxException extends Exception {
    /**
     * Constructs a {@code MaxException} with the specified detail message.
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public MaxException(String message) {
        super(message);
    }
}
