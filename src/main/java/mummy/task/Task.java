package mummy.task;


/**
 * Represents a task.
 */
public abstract class Task {
    private final String description;

    private boolean isDone;

    /**
     * Creates a new Task with the given description.
     * The task is initially set as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone      the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task that can be written to a file.
     *
     * @return The string representation of the task for file storage.
     */
    public abstract String toFileRecord();

    /**
     * Marks the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    };

    /**
     * Sets the task as undone.
     */
    public void setAsUndone() {
        this.isDone = false;
    };

    /**
     * Returns the status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return (isDone() ? "[X] " : "[ ] ") + description;
    }
}
