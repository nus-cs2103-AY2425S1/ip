package jackbean.task;

/**
 * Represents a task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with a description.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     * This JavaDoc was written by GitHub Copilot.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     * This JavaDoc was written by GitHub Copilot.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return Whether the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
