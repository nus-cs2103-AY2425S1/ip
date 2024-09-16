package moody.tasks;

/**
 * Represents a to-do task.
 * A to-do is a type of Task that does not include any additional date or time information.
 */
public class Todo extends Task {

    /**
     * Creates a To-do with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the To-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the To-do to a format suitable for saving to a file.
     * The format includes the task type and the description.
     *
     * @return A string representation of the To-do in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns a string representation of the To-do for display purposes.
     * The format includes the task type and the description.
     *
     * @return A string representation of the To-do.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
