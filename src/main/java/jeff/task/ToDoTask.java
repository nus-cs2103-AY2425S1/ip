package jeff.task;

/**
 * Represents a to-do task.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for the ToDoTask Class.
     * Task is marked as not done.
     *
     * @param description Description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructor for the ToDoTask Class.
     * Task may be marked as done or not done depending on the input.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done or not.
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file string representation of the task to be stored in the task list text file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
