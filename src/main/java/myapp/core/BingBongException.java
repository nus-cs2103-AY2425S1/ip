package myapp.core;

/**
 * The BingBongException class represents a custom exception used in the BingBong application.
 * It extends the {@link Exception} class and is thrown when the application encounters specific errors.
 */
public class BingBongException extends Exception {

    /**
     * Constructs a new BingBongException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public BingBongException(String message) {
        super(message);
    }
}
