package chatsy.tasks;

/**
 * Represents a task that needs to be done without a specific deadline or time range.
 */
public class TodoTask extends Task {

    /**
     * Constructs a {@code TodoTask} with the specified description.
     *
     * @param description The description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a {@code TodoTask} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of this task, including its status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }

    /**
     * Returns the string format of this task for saving to a file.
     *
     * @return The save format of the task as a string.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
