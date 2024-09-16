package thanos.exceptions;

/**
 * Exception thrown to indicate that a date is invalid.
 */
public class InvalidDateException extends Exception {
    /**
     * Constructs an {@code InvalidDateException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
