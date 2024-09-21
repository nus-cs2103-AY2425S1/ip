package meep.task;

/**
 * The {@code Todo} class represents a task that does not have a specific deadline or time frame.
 * It extends the {@link Task} class by providing a simple task type.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description A description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the formatted string representation of the todo task for saving to a file.
     * The format includes the task type and completion status, followed by the description.
     *
     * @return The formatted string for saving the todo task.
     */
    @Override
    public String getSaveFormat() {
        return "T|" + super.getSaveFormat();
    }

    /**
     * Returns the string representation of the todo task.
     * The format includes the task type and description.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
