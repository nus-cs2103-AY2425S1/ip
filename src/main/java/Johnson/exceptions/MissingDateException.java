package Johnson.exceptions;

/**
 * Exception thrown when a date is missing for a task that requires one.
 */
public class MissingDateException extends Exception {
    public MissingDateException(String message) {
        super(message);
    }
}
