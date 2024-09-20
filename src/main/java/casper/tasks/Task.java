package casper.tasks;

/**
 * Represents a task with a description and a completion status.
 * Provides methods for marking the task as done or undone, and formatting the task information for display and saving.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and sets its completion status to false.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description A brief description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string in the format: "[status icon] description".
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the specified keyword is present in the task's description.
     * <p>
     * This method searches for the given keyword within the task's description
     * and returns {@code true} if the keyword is found. If the description or
     * the keyword is {@code null}, the method returns {@code false}.
     * </p>
     *
     * @param keyword The keyword to search for in the task's description.
     *                This can be any non-null string.
     * @return {@code true} if the keyword is found in the description,
     *         {@code false} if the description or keyword is {@code null}
     *         or if the keyword is not found.
     */
    public boolean isKeywordInDescription(String keyword) {
        if (this.description == null || keyword == null) {
            return false;
        }
        return this.description.contains(keyword);
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return A string suitable for saving to a file.
     */
    public abstract String toSaveString();
}

