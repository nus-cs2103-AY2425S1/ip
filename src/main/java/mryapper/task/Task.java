package mryapper.task;

/**
 * Contains all common functions and properties for tasks created by the ChatBot.
 * All tasks have a description and can be marked as done.
 */
public abstract class Task {
    private final String description;
    private boolean isDone = false;

    /**
     * Creates a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns 1 if the task is done and 0 otherwise.
     *
     * @return 1 or 0 based on whether the task is done.
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a status icon to show whether the task is done.
     *
     * @return "X" if the task is done and " " if the task isn't.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string following the format of how the task is stored in the data file.
     *
     * @return The string to be written when storing the task in data text file.
     */
    public abstract String getDataString();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
