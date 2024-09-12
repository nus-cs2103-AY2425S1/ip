package asta.task;

/**
 * Represents a task with a description and a completion status. This is an abstract class and serves as a base class
 * for specific types of tasks like ToDo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description. The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. "[X]" if the task is done, "[ ]" otherwise.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file. This method must be
     * implemented by subclasses to provide specific file formats.
     *
     * @return A string representing the task for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
