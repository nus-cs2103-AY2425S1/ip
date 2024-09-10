package mira;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    private final String description; // The task's description
    private boolean isDone; // Indicates if the task is completed

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and isDone.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status True to mark the task as done, false to mark it as not done.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * An "X" indicates the task is done; a space indicates it is not done.
     *
     * @return A string containing "X" if the task is done, otherwise a space.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the string format of task to be saved for storage.
     *
     * @return A specified format string for storage.
     */
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the task, including its status icon.
     *
     * @return The task's status icon followed by its description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Checks if the task's description contains the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the description contains the keyword, otherwise false.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
