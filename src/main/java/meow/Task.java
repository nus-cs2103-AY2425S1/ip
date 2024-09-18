package meow;

import java.io.Serializable;

/**
 * Represents an abstract task that can be marked as done or not done.
 * This class is extended by more specific types of tasks such as Event, Deadline and ToDo.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is marked, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The string version of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
