package tasks;

import java.io.Serializable;

/**
 * Class that represents a {@code Task}.
 * <p>
 * These tasks are added to the {@code TaskList}.
 */
public class Task implements Serializable {
    protected final String description;
    protected boolean isDone = false;

    /**
     * Constructs a {@code Task} object.
     * This is only called by inherited classes to set the task description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns a task's done or undone status.
     * @return Either [X] or [ ] for done and undone respectively.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Marks a task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns the task description.
     */
    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }
}
