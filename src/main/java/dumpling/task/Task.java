package dumpling.task;

/**
 * Abstract Task class, which Event, Deadline and ToDo inherit from
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of task
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return 'X' if marked, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if the description of this task has the given substring
     * @param substring Target substring to search for
     * @return True if substring is part of description, false otherwise
     */
    public boolean hasSubstring(String substring) {
        for (int i = 0; i <= description.length() - substring.length(); i++) {
            if (this.description.substring(i, i + substring.length()).equals(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mark item as done
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Unmark item as done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get the string representation of the task in the format for saving
     *
     * @return String representation of task in saving format
     */
    public abstract String getTaskForSaving();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
