package charlotte.task;

/**
 * Represents a task that only contains a description.
 * This task does not have any specific time or date associated with it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task to a format suitable for saving to a file.
     *
     * @return A string representing the ToDo task in a file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes the task type, status icon, and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
