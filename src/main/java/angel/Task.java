package main.java.angel;

/**
 * Represents a task with a description and a completion status.
 * This is an abstract class meant to be extended by specific types of tasks (e.g., ToDo, Deadline, Event).
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     * The task is initialized as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the status icon ("X" if the task is done, " " otherwise).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in a saveable format.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Provides a string representation of the task.
     * This method must be implemented by subclasses.
     *
     * @return A string representation of the task.
     */
    public abstract String toString();
}
