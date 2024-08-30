package task;

/**
 * Abstract class containing what task needs
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status icon
     * @return Status icon as string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark as done
     * @return true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark as not done
     * @return true
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Get description
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return string of Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * To be overridden to format string to be saved.
     * @return String to be saved.
     */
    public abstract String toSave();
}
