package dgpt.exception;

/**
 * Thrown to indicate that the command is not recognised.
 */
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
