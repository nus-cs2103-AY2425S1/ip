package dumpling.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return 'X' if marked, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark item as done
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Unmark item as done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get the string representation of the task in the format for saving
     *
     * @return String representation of task in saving format
     */
    public abstract String getTaskForSaving();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
