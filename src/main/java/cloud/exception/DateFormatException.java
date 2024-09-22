package cloud.exception;

/**
 * Represents an exception for handling DateTime related errors.
 */
public class DateFormatException extends CloudException {
    public DateFormatException(String message) {
        super(message);
    }
}
