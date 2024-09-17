package tasks;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     *
     * @param description The to-do description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do, including its status and description.
     *
     * @return A formatted string representing the to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format of the to-do for saving to file.
     *
     * @return A formatted string representing the to-do for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
