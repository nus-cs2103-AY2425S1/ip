package fanny.task;

/**
 * Represents a todo task.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {

    /**
     * Constructs an {@code ToDo} with the specified description.
     *
     * @param description A description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its status,
     * and description.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
