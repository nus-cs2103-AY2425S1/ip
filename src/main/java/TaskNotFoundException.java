/**
 * This exception is thrown when a task is not found.
 * <p>
 * The {@code TaskNotFoundException} is a custom exception that extends the {@code Exception} class.
 * It is used to indicate that a task does not exist in task list created.
 * </p>
 */
public class TaskNotFoundException extends Exception {
    @Override
    public String toString() {
        return "OOPS!!! The task cannot be found.";
    }
}
