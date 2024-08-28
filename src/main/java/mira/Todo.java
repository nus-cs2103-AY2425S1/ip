package mira;

/**
 * Represents a ToDo task without any date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the specified description and isDone.
     *
     * @param description The description of the ToDo task.
     * @param isDone The status of the ToDo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string format of Todo task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    /**
     * Returns a string representation of the ToDo task.
     * It includes its type, status icon, and description.
     *
     * @return The task's type "[T]", status icon, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
