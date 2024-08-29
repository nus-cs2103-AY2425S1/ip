package james;

/**
 * Represents a task with a description and a completion status.
 * Provides methods to mark the task as done or not done, and to format the task for output or storage.
 */
public abstract class Task {
    private String description;
    private Boolean marked;

    /**
     * Creates a new Task with the specified description and completion status.
     * Throws an exception if the description is empty.
     *
     * @param desc The description of the task.
     * @param mark The completion status of the task.
     * @throws MissingDescriptionException If the description is empty.
     */
    public Task(String desc, Boolean mark) throws MissingDescriptionException {
        if (desc.isEmpty()) {
            throw new MissingDescriptionException("Looks like you left out the description of the task, please try again.");
        }
        this.description = desc;
        this.marked = mark;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.marked = false;
    }

    /**
     * Returns a string representation of the task for display purposes.
     * The format is "[X] description" if the task is marked, or "[ ] description" if not.
     *
     * @return The formatted task string.
     */
    public String printTask() {
        String mark = marked ? "X" : " ";
        return String.format("[%s] %s", mark, description);
    }

    /**
     * Returns a string representation of the task for storage purposes.
     * The format is "markedStatus | description".
     *
     * @return The task in a format suitable for storage.
     */
    public String toFileFormat() {
        return String.format("%d | %s", marked ? 1 : 0, description);
    }
}
