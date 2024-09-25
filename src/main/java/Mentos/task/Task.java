package Mentos.task;

public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructs a Task object with the specified description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates the description of the task.
     *
     * @param description The new description of the task.
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status icon for the task, indicating if it is done.
     *
     * @return "X" if the task is done, otherwise a blank space " ".
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
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task, including its status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
