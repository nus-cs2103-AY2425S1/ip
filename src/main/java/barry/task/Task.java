package barry.task;

/**
 * Represents a task given by user in the chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * Task will be marked as undone initially.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is complete.
     * " " indicates that the task is incomplete.
     *
     * @return The completion icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     *
     * @return true if the task was marked as done, false otherwise.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the task in a format to save the file.
     * The format consists of a status indicator (1 for done, 0 for undone)
     * followed by the task description.
     *
     * @return A String representing the task in save format.
     */
    public String saveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
