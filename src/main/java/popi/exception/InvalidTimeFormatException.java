package popi.exception;

/**
 * Represents an exception thrown when the time format provided is invalid.
 */
public class InvalidTimeFormatException extends PopiException {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}
