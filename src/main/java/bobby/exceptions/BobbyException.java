package bobby.exceptions;

/**
 * The {@code BobbyException} class is a custom exception that serves as the base class
 * for all exceptions related to the Bobby application. It extends the {@code Exception} class
 * and provides a way to handle various error scenarios specific to the application.
 */
public class BobbyException extends Exception {

    /**
     * Constructs a new {@code BobbyException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public BobbyException(String message) {
        super(message);
    }
}

