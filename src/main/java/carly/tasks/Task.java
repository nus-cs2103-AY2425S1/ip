package carly.tasks;

import java.text.MessageFormat;

/**
 * Represents an abstract task that can be marked as done or undone.
 * Each task has a description and a completion status.
 * Child classes extended from Task class represents specific types of tasks.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /**
     * Indicates whether the task is done.
     * True if the task is completed, false otherwise.
     */
    protected boolean isDone;

    /** Constructs a new Task with the specified description. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /** Returns the status icon of the task, indicating whether it is done or not. */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the task as done and returns the updated Task object. */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /** Unmarks the task and returns the updated Task object. */
    public Task unmarkAsDone() {
        this.isDone = false;
        return this;
    }

    /** Returns a string representation of the task, including its status icon and description. */
    @Override
    public String toString() {
        return MessageFormat.format("[{0}] {1}", this.getStatusIcon(), this.getDescription());
    }

}
