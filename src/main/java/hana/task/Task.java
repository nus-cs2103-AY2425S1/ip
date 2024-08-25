package hana.task;

/**
 * Represents a task with a description and completion status.
 * This is a base class for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with description.
     *
     * @param description The description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Change task completion status.
     *
     * @param isDone Completion status to change to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the details of the task.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    public abstract String fileString();
}
