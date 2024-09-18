package jay.task;

/**
 * Represents an exception when the description of a task is invalid.
 */
public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
