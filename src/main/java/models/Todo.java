package models;

/**
 * Represents a todo task in the task management system.
 * A todo is a task that only has a description and a completion status,
 * without any associated time or deadline.
 *
 * <p>
 * This class extends the {@code Task} class and implements
 * the required methods for serializing and displaying a todo task.
 * </p>
 *
 * @see Task
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} with the specified description.
     * The task is initialized as not completed.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone The completion status of the task; {@code true} if the task is completed, {@code false} otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Serializes the todo task to a string format.
     * The format is "T|<isDone>|<description>", where {@code isDone} is 1 if the task is completed and 0 otherwise.
     *
     * @return A string representing the serialized form of the todo task.
     */
    @Override
    public String serialize() {
        return String.format("T|%s|%s", this.getIsDone() ? "1" : "0", this.getDescription() );
    }

    /**
     * Returns the string representation of the todo task.
     * The format is "[T][<statusIcon>] <description>", where {@code statusIcon} is "X" if the task is completed or a space otherwise.
     *
     * @return A string representing the todo task for display.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
