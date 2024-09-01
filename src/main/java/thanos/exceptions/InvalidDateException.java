package thanos.exceptions;

/**
 * Exception thrown to indicate that a date is invalid.
 * <p>
 * The {@code InvalidDateException} class extends {@code Exception} and is used to signal issues with dates
 * that are invalid or improperly formatted. It provides a custom error message to indicate the nature of the problem.
 * </p>
 */
public class InvalidDateException extends Exception {
    /**
     * Constructs an {@code InvalidDateException} with the specified detail message.
     *
     * @param message the detail message to be included in the exception. This message provides information
     *                about why the date is considered invalid and can be used for debugging or user feedback.
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
