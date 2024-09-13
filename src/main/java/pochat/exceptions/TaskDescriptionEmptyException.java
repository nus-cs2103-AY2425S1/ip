package pochat.exceptions;

/**
 * Throws when the bot attempts to read the stored chat history
 *     but the file is not file at the directory as specified
 */
public class TaskDescriptionEmptyException extends Exception {
    /**
     * Informs the user what went wrong (task description was left empty)
     */
    public TaskDescriptionEmptyException() {
        super("Task Description cannot be empty!!");
    }
}
