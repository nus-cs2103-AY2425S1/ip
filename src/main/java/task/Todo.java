package task;

/**
 * Represents a todo task with a description and a completion status.
 */
public class Todo extends Task {
    /**
     * Initializes a Todo task with a description. The task is initially marked as not done.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Provides a string representation of the todo task suitable for saving to a file.
     *
     * @return A string representation of the todo task for file storage.
     */
    @Override
    public String toFile() {
        return "T|" + getStatusIcon() + "|" + this.description;
    }

    /**
     * Provides a string representation of the todo task for display.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
