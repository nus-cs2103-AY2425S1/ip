package dudu.exception;

/**
 * Represents a custom checked exception where the user fails to input a date for deadline or event task.
 */
public class MissingDateTimeException extends Exception {
    /**
     * Constructs an MissingDateTimeException.
     *
     * @param message Error message.
     */
    public MissingDateTimeException(String message) {
        super(message);
    }
}
