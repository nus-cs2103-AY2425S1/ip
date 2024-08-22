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
}
