package botty.exceptions;

/**
 * Exception thrown when task list is empty
 */
public class TaskListEmptyException extends BottyException {
    /**
     * Constructs a new {@code TaskListEmptyException}.
     */
    public TaskListEmptyException() {
        super("Your list is empty! Add a task with the todo, deadline or event command.");
    }
}
