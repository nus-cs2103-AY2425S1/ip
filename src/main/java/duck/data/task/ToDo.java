package duck.data.task;

/**
 * Represents a simple to-do task that does not have a specific deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description and sets the task as not done.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param isDone Whether the to-do task is marked as done.
     * @param description The description of the to-do task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Converts the ToDo task to a format suitable for saving to a file.
     * The format is "T | {status} | {description}".
     *
     * @return A string representation of the ToDo task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns a string representation of the ToDo task for display.
     * The format is "[T][status] {description}".
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
