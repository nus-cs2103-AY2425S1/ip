package dude.task;

/**
 * Represents a task with only description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task's data into a string format for saving to a file.
     *
     * @return A string representing the ToDo task's data for saving.
     */
    @Override
    public String taskToStringData() {
        return "T" + super.taskToStringData();
    }

    /**
     * Returns a string representation of the ToDo task, including its status and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
