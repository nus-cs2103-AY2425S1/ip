package popi.exception;

/**
 * Represents an exception thrown when the task number provided is invalid.
 */
public class InvalidTaskNumberException extends PopiException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
