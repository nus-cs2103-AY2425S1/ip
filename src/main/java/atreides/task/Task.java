package atreides.task;

/**
 * represents the tasks that the user can add into the list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this("");
    }

    public Task(String description) {
        this(description, false);
    }

    /**
     * Construct a Task with the given description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Boolean value indicating if the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an icon string based on the completion status of the task.
     *
     * @param trueString The string to return if the task is marked as done.
     * @param falseString The string to return if the task is not marked as done.
     * @return trueString if the task is done, otherwise falseString.
     */
    public String getStatusIcon(String trueString, String falseString) {
        return (isDone ? trueString : falseString);
    }

    /**
     * Marks the task as done or not done based on the provided Boolean value.
     *
     * @param var Boolean value indicating whether the task should be marked as done (true) or not done (false).
     */
    public void markDone(Boolean var) {
        this.isDone = var;
    }

    /**
     * @return string to write into the .txt file for storage and subsequent reading
     */
    public String write() {
        return " | " + this.getStatusIcon("1", "0") + " | " + this.description;
    }

    /**
     * Checks if two Task objects are equal based on their description.
     *
     * @param obj The object to be compared with this Task.
     * @return true if the object is the same as this Task or if it is a Task instance with the same description.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Task other) {
            return this.description.equals(other.description);
        }
        return false;
    }

    /**
     * @return the representation of the task and whether its completed
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon("X", " ") + "] " + this.description;
    }
}
