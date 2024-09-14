package seedu.task;

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
     * Gets status icon
     * @return Status icon as string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets description
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
