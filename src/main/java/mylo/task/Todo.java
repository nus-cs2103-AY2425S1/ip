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

    /**
     * Compares this {@code Todo} task to the specified object for equality.
     * <p>This method checks if the given object is a {@code Todo} and compares their titles
     * to determine equality. Two {@code Todo} tasks are considered equal if their titles are the same.</p>
     *
     * @param o The object to be compared for equality with this {@code Todo}.
     * @return {@code true} if the specified object is a {@code Todo} with the same title,
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo other) {
            return this.getTitle().equals(other.getTitle());
        }
        return false;
    }
}
