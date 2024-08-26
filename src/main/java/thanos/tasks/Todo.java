package thanos.tasks;

/**
 * Represents a task with no specific deadline or event time.
 * It includes a description and a status indicating whether the task is done or not.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} object with the specified description.
     *
     * @param description the description of the {@code Todo} task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     *
     * @return a string representation of the {@code Todo} task in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the {@code Todo} task suitable for saving to a file.
     *
     * @return a string representation of the {@code Todo} task in the format "T | [status] description".
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
