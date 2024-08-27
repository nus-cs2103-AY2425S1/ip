package megamind.exception;

/**
 * Exception thrown when a task is not found.
 */
public class TaskNotFoundException extends Exception {

    /**
     * Constructor for TaskNotFoundException
     *
     * @param message the message to be displayed
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
