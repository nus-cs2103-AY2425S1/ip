package Tasks;

/**
 * Abstract base class to provide a template for a task object.
 */
public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone;

    /**
     * Stores the task description.
     * Sets the task status to be uncompleted
     *
     * @param description Description of the task.
     */
    protected Task(String description) {
        DESCRIPTION = description;
        this.isDone = false;
    }

    /**
     * Gets the task description.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * Sets the task status to be completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task status to be uncompleted
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the task status as an icon.
     *
     * @return Icon representing task status.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    };

    /**
     * Gets the type of the task.
     *
     * @return Task type.
     */
    abstract public String getTaskType();

    /**
     * Gets extra information about the task.
     * (Eg: due date, start date)
     *
     * @return Extra information about the task.
     */
    abstract public String getExtraInfo();
}
