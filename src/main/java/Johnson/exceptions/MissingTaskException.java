package Johnson.exceptions;

/**
 * Exception thrown when a task is missing for a command that requires one.
 */
public class MissingTaskException extends Exception {
    public MissingTaskException(String message) {
        super(message);
    }
}