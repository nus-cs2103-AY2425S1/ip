package bao.task;

/**
 * The ToDo class represents a task that only has a description.
 * It extends the Task class, inheriting its properties and methods.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, prefixed with "T" to indicate it is a todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "T | " + super.toString() + getTagsAsString();
    }

    /**
     * Returns a string representation of the todo task for saving, prefixed with "T" to indicate it is a todo task.
     *
     * @return String representation of the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description.trim() + getTagsAsString();
    }
}
