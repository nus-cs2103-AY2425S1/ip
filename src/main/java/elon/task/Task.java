package elon.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a status indicating whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description and status.
     *
     * @param description description of the task
     * @param isDone whether the task is completed or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Returns a status icon with "X" representing the task as done and a space " " as not.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, otherwise false
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * The format is "[statusIcon] description", where statusIcon is "X" if the task is done,
     * or a space " " if not done, and description is the description of the task.
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done by setting isDone to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting isDone to false;
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task suitable for saving to a file.
     *
     * @return the description of the task
     */
    public String toFileString() {
        return this.description;
    }

    public void snooze(LocalDateTime newDate) {
    }
}
