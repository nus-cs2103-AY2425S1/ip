package sigma.task;

/**
 * Represents a task that needs to be done, without any specific deadline or event timing.
 * Inherits from the {@link Task} class.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts this {@code Todo} task to a string representation suitable for saving to a file.
     *
     * @return the string representation of this task in the format "T | status | description"
     */
    @Override
    public String stringify() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns the string representation of this {@code Todo} task.
     *
     * @return the string representation of this task in the format "[T][status] description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
