package arts.task;

/**
 * Represents a Todo task, a specific type of Task with no additional
 * attributes beyond its description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
    }

    /**
     * Returns a string representation of the todo task, including its type and description.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        assert description != null : "Description should not be null when converting to string";
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task formatted for file storage.
     * This includes the task type, completion status, and description.
     *
     * @return A string formatted for storing the todo task in a file.
     */
    @Override
    public String toFileFormat() {
        assert description != null : "Description should not be null when converting to file format";
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
