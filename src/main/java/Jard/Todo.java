package Jard;

/**
 * Represents a todo task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task for file storage.
     *
     * @return A string representing the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns the description of the todo task.
     *
     * @return The description of the todo task.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A formatted string of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
