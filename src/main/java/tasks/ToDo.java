package tasks;

/**
 * Represents a "To Do" task.
 * A ToDo task is a simple task with a description but no specific date or time associated with it.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "todo";

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string format of the ToDo task for saving to a file.
     *
     * @return A string formatted for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns the type of the task as a string ("todo").
     *
     * @return The string "todo" representing the task type.
     */
    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

}
