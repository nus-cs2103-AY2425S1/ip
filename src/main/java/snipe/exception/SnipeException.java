package snipe.exception;

/**
 * The {@code snipe.exception.SnipeException} class represents a custom exception that is thrown
 * when the snipe.core.Snipe chat bot encounters an error related to user input or task processing.
 */
public class SnipeException extends Exception{

    /**
     * Constructs a new {@code snipe.exception.SnipeException} with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public SnipeException(String message) {
        super(message);
    }
}
