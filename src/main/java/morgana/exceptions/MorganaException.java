package morgana.exceptions;

/**
 * Represents a custom exception used in the Morgana application.
 * This exception is thrown when specific errors related to the application's
 * functionality occur.
 */
public class MorganaException extends Exception {
    /**
     * Constructs a {@code MorganaException} with the specified detail message.
     *
     * @param message The detail message describing the specific error that occurred.
     */
    public MorganaException(String message) {
        super(message);
    }
}
