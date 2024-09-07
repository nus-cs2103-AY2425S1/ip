package elysia.task;

import elysia.exception.EmptyDescriptionException;

/**
 * Represents a basic task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a basic task with the description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints task's status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a completed task.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Saves the task into the txt file.
     **/
    public abstract String saveToTxt();

    //...
}
