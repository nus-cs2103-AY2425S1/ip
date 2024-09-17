package axel;

/**
 * Represents a task that does not have a deadline or a time range.
 * Inherits from {@link Task}.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a {@code ToDoTask} with the specified task name.
     *
     * @param taskName A description of the task
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the {@code ToDoTask}.
     * The format is: "[T][status]".
     * For example, "[T][ ]".
     *
     * @return A formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of this task suitable for saving to a file.
     * The format is: "T | [status] | [description]".
     * For example, "T | 0 | Buy groceries".
     *
     * @return A string representation of the task in file format
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getTaskName();
    }
}
