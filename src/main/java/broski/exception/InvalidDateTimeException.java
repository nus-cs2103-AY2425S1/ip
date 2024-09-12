package broski.exception;

/**
 * Exception class to handle invalid date and time input by user.
 */
public class InvalidDateTimeException extends IllegalArgumentException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
