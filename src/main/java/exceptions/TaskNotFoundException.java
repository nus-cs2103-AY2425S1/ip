package exceptions;

/**
 * Thrown to indicate that a specified task could not be found in the task list.
 * <p>
 * This exception is used when a task-related operation encounters a reference to a non-existent task.
 * </p>
 */
public class TaskNotFoundException extends Exception {
    /**
     * Constructs a {@code TaskNotFoundException} with the specified detail message.
     *
     * @param message the detail message, which is saved for later retrieval by the {@link #getMessage()} method
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}

