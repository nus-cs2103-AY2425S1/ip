package Optimus;

/**
 * Abstract class representing a task in the Optimus application.
 * A {@code Task} has a description and a status indicating whether it is done.
 * Subclasses of {@code Task} must implement the {@code toSaveString} method for saving the task's data.
 */
public abstract class Task {

    protected String description;  // The description of the task.
    protected boolean isDone;  // The status of the task, indicating whether it is completed.

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially not done.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing the completion status of the task.
     * "X" indicates that the task is done, and " " (space) indicates that it is not done.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as not done by setting its status to false.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to be implemented by subclasses to return a formatted string
     * for saving the task's data to a file.
     *
     * @return the formatted string for saving the task.
     */
    public abstract String toSaveString();
}
