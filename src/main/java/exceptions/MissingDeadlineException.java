package exceptions;

/**
 * Thrown to indicate that a deadline-related operation encountered an invalid or missing deadline value.
 * <p>
 * This exception is used when a deadline-related task or operation does not provide a valid deadline
 * or when the deadline information is missing or incorrect.
 * </p>
 */
public class MissingDeadlineException extends Exception {
    /**
     * Constructs a {@code MissingDeadlineException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public MissingDeadlineException(String message) {
        super(message);
    }
}