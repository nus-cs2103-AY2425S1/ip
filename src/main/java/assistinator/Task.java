package assistinator;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises a task.
     * @param description Details of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Convert task to formatted string
     * @return formatted string
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Generate string to print onto file
     * @return String for file
     */
    public abstract String toFileString();

    /**
     * Checks if task description contains keyword.
     * @param keyword Search keyword.
     * @return Whether description contains keyword.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}
