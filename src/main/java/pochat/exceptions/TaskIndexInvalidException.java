package pochat.exceptions;

/**
 * Throws when the task index is invalid
 */
public class TaskIndexInvalidException extends Exception {
    /**
     * Informs the user what went wrong (task index was invalid)
     */
    public TaskIndexInvalidException() {
        super("Task index is invalid!!");
    }
}
