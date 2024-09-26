package jeriel.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string indicating the status of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the task is done, false otherwise.
     *
     * @return boolean representing task completion status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Abstract method to convert the task to save format.
     *
     * @return string in save format
     */
    public abstract String toSaveFormat();

    /**
     * Returns a string representation of this task.
     *
     * @return a string in the format "[ ] description" or "[X] description"
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
