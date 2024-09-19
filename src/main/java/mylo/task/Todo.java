package mylo.task;

/**
 * Represents a Todo task in the task management system.
 * <p></p>
 * <p>A Todo task is a simple task that needs to be done. It contains a title and a status
 * indicating whether it is completed or not.</p>
 *
 * @author cweijin
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified title and sets the status to not done.
     *
     * @param title The title of the Todo task.
     */
    public Todo(String title) {
        this(title, false);
    }

    /**
     * Creates a Todo task with the specified title and status.
     *
     * @param title The title of the Todo task.
     * @param isDone The status of the Todo task; {@code true} if completed, {@code false} otherwise.
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Converts the Todo task data into a {@code String} for storage.
     *
     * @return A {@code String} representing the storage format of the Todo task.
     */
    @Override
    public String storageFormat() {
        return String.format("TODO | %s | %s", super.completionStatus(), super.getTitle());
    }

    /**
     * Returns a string representation of the Todo task, including its status and title.
     *
     * @return A {@code String} representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
