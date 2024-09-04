package optimus;

/**
 * Abstract class representing a task in the Optimus application.
 * Subclasses of Task must implement the toSaveString method for saving the task's data.
 */
public abstract class Task {

    protected String description;  // The description of the task.
    protected boolean isDone;  // The status of the task, indicating whether it is completed.

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a symbol representing the completion status of the task.
     * "X" indicates that the task is done, and " " indicates that it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the completion status of the task.
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
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Abstract method to be implemented by subclasses to return a formatted string
     * for saving the task's data to a file.
     */
    public abstract String toSaveString();
}
