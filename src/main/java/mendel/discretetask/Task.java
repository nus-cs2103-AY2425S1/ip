package mendel.discretetask;

/**
 * Represents an abstract task with a description and a completion status.
 * This class serves as parent for specific tasks such as Todo, Event, and Deadline.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description. The task is initially marked as not done.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * If the task is done, the icon is "X"; otherwise, it is an empty space.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getStatus() {
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
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Parses the task details into a string format suitable for storage in a database.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return A string containing the task details formatted for database storage.
     */
    public abstract String parseDetailsForDB();

    /**
     * Determines if the given date matches the due date or relevant date for this task.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param formattedDate The date to be compared in a formatted string.
     * @return true if the date matches, false otherwise.
     */
    public abstract boolean isTargetDueDate(String formattedDate);

    public abstract boolean isIncompleteWithinTargetDueDate(String formattedDate);

    public abstract boolean isMatchingDescription(String matchingString);

    /**
     * Returns a string representation of the task, including its status icon and formatted description from subclasses.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
