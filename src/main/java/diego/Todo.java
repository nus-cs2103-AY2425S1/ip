package diego;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string describing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X]" : "[ ]") + " " + description;
    }

    /**
     * Returns the file format string of the to-do task for storage.
     *
     * @return A string suitable for storing in a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
