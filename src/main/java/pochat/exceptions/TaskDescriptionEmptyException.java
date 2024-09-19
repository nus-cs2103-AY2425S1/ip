package pochat.exceptions;

/**
 * Throws when the task description is empty
 */
public class TaskDescriptionEmptyException extends Exception {
    /**
     * Informs the user what went wrong (task description was left empty)
     */
    public TaskDescriptionEmptyException() {
        super("Task description cannot be empty!! Please try again");
    }
}
