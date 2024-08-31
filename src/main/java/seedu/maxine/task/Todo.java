package seedu.maxine.task;

public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     * <p>
     * The description is passed to the superclass constructor and is converted to lowercase.
     * </p>
     *
     * @param s The description of the Todo task.
     */
    public Todo(String s) {
        super(s);
    }

    /**
     * Returns a string representation of the Todo task in a human-readable format.
     * <p>
     * The format includes the task type indicator "[T]", the status icon, and the description of the task.
     * </p>
     *
     * @return A string representation of the Todo task formatted as "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for file storage.
     * <p>
     * The format includes the task type indicator "T", followed by the status and description of the task.
     * </p>
     *
     * @return A string representation of the Todo task suitable for file storage.
     */
    @Override
    public String writeToFile() {
        return "T" + super.writeToFile();
    }

}
