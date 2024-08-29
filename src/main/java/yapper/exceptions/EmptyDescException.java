package yapper.exceptions;

/**
 * Exception thrown when a task description is missing or empty.
 */
public class EmptyDescException extends YapperException {
    public EmptyDescException(String message) {
        super("No description given " + message);
    }
}
