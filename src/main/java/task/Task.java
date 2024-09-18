package task;

import exception.CommandFoundButInvalidException;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} object with the specified description.
     *
     * @param description the description of the task
     * @throws CommandFoundButInvalidException if the description is invalid
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     * The completion status is indicated by "[X]" for done and "[ ]" for not done.
     *
     * @return a string representation of the task
     */
    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the initial description of the event in a format suitable for storage.
     * This format includes the task type, completion status, and the original description.
     *
     * @return a string representation of the event suitable for storage
     */
    public String getInitDesc() {
        return this.description;
    };

}
