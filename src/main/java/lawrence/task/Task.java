package lawrence.task;

/**
 * Represents a real-life task that needs to be completed.
 */
public abstract class Task {
    private final String description;
    private boolean complete;

    /**
     * Constructor. Creates a {@link Task} object with the specified
     *task description.
     *<p>
     *The task will be marked as incomplete by default.
     *</p>
     * @param description the name of the task
     */
    public Task(String description) {
        this.description = description;
        complete = false;
    }

    /**
     * Constructor. Creates a {@link Task} object with the specified
     * task description and completion status.
     * <p>
     * The task can be marked as complete or incomplete on creation.
     * </p>
     *
     * @param description the name of the task
     * @param isComplete indicates whether the task is complete
     */
    public Task(String description, boolean isComplete) {
        this.description = description;
        complete = isComplete;
    }

    /**
     * Sets the completion status of the task to the specified value.
     *
     * @param complete indicates whether the task is complete
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Returns a string representation of the object in a
     * standardised save format.
     *
     * @return a string representation of the object in save format
     */
    public String toSaveFormat() {
        return String.format("%s | %s", complete ? "1" : "0", description);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", complete ? "X" : " ", description);
    }
}
