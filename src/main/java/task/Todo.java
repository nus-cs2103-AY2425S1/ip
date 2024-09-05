package task;

/**
 * Represents a to-do task.
 * <p>
 * The {@code Todo} class extends {@link Task} to represent a task that needs to be done.
 * It provides methods to display the task and to retrieve a formatted string suitable for database storage.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description.
     * <p>
     * Initializes the to-do task with the given description. The task is initially not done.
     * </p>
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} with the specified description and completion status.
     * <p>
     * Initializes the to-do task with the given description and completion status.
     * </p>
     *
     * @param description The description of the to-do task.
     * @param isDone      A boolean indicating whether the to-do task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the {@code Todo} for display purposes.
     * <p>
     * The string format is "[T][task details]". The task details include the status icon and description.
     * </p>
     *
     * @return A string representation of the {@code Todo} for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the {@code Todo} suitable for database storage.
     * <p>
     * The string format is "T | [isDone] | [description]". The completion status and description are included.
     * </p>
     *
     * @return A string representation of the {@code Todo} suitable for database storage.
     */
    @Override
    public String getDatabaseString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
