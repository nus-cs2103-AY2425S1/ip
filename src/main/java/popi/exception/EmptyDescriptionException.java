package popi.exception;

/**
 * Represents an exception thrown when the description of a task is empty.
 */
public class EmptyDescriptionException extends PopiException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
