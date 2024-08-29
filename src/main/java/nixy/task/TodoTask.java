package nixy.task;

/**
 * Represents a Todo task.
 */
public class TodoTask extends Task {
    /**
     * Creates a Todo task with the specified description.
     *
     * @param description The description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Creates a Todo task with the specified description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The done status of the task.
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the type label of the task.
     *
     * @return The type label of the task.
     */
    private String getTypeLabel() {
        return "T";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTypeLabel(), super.toString());
    }

    /**
     * Returns the string representation of the task in data format (for saving to file)
     *
     * @return The data string.
     */
    @Override
    public String toDataString() {
        return String.format("%s|%s", this.getTypeLabel(), super.toDataString());
    }
}
