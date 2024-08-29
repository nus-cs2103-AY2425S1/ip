package gavinchatbot.task;

/**
 * Represents a task that is a to-do.
 * Inherits from the Task class.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task in a format suitable for saving to a file.
     *
     * @return The string representation of the to-do task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
