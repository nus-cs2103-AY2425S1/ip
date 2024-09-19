package bottle.task;

/**
 * Represents a simple task without a specific deadline or time frame.
 * Extends the Task class to provide the basic functionality of a ToDo task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo with the specified description.
     *
     * @param desc the description of the ToDo task
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns a string representation of the Todo task, including its type and description.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task formatted for file storage.
     * The format includes the task type and completion status.
     *
     * @return a string representation of the Todo task in a file-friendly format
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | ";
    }
}
