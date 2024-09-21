package lutchat;

/**
 * The Task class represents a task in the task list.
 * It serves as an abstract base class for different types of tasks such as Todo, Deadline, and Event.
 */
public abstract class Task {
    private boolean isDone;
    private String description;

    /**
     * Constructs a Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a file-friendly format string.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    public String toFileFormat() {
        return getTaskType() + " | "
                + (isDone ? "1" : "0") + " | "
                + description
                + additionalDescDetailsToFileFormat();
    }

    /**
     * Checks if the specified string is contained within the task's description.
     *
     * @param str the string to check for within the task's description
     * @return true if the description contains the specified string, false otherwise
     */
    public boolean contains(String str) {
        if (description.contains(str)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the type of the task.
     * This method must be implemented by subclasses to specify the task type.
     *
     * @return A string representing the type of the task.
     */
    public abstract String getTaskType();

    /**
     * Returns additional details of the task to be included in the file format.
     * This method must be implemented by subclasses to specify additional task details.
     *
     * @return A string representing additional task details for file storage.
     */
    public abstract String additionalDescDetailsToFileFormat();

    /**
     * Returns a string representation of the task.
     * The format indicates whether the task is done or not.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
