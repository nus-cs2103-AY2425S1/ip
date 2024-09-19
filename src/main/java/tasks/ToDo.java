package tasks;

/**
 * Represents a to-do task, which is a simple task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the to-do task for storage.
     *
     * @return A string representing the to-do task in a format suitable for storage.
     */
    @Override
    public String toDataFormat() {
        return "todo | " + super.toDataFormat();
    }

    /**
     * Returns a formatted string representation of the to-do task for display,
     * including its status icon and description.
     *
     * @return A string representing the to-do task with its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
