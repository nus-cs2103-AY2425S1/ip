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
    }

    /**
     * Returns a string representation of the todo task, including its type and description.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
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
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
