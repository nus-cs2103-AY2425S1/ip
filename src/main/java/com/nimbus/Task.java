package com.nimbus;

/**
 * Task represent a task in real life, it contains a description and an
 * indicator indicating whether it has been done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void setNotDone() {
        isDone = false;
    }

    /**
     * Get the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the status icon for whether the task has been done.
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the task in pure String form.
     * @return File format of the task.
     */
    public abstract String toFileFormat();


    /**
     * A String to indicate the type of the task.
     * @return Type of the Task.
     */
    public abstract String getTypeIcon();

    /**
     * Check if the current Task contains the keyword.
     * @param keyword The keyword to search for.
     * @return True if it contains the keyword.
     */
    public abstract boolean contains(String keyword);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
