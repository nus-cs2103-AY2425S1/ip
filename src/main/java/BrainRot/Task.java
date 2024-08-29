package BrainRot;

/**
 * The BrainRot.Task class represents a generic task with a description and a completion status.
 * It serves as a base class for specific types of tasks, such as BrainRot.ToDo, BrainRot.Deadline, and BrainRot.Event.
 * This class provides common functionality to mark and unmark tasks as well as to represent
 * the task's status as a string.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new BrainRot.Task with the specified description.
     * By default, the task is not marked as completed.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * An "X" indicates that the task is completed, and a blank space indicates it is not.
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     * This method must be implemented by subclasses to include their specific details.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
