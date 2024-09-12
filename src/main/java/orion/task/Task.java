package orion.task;

/**
 * Represents a task with a unique ID, description, and completion status.
 *
 * <p>
 * This is an abstract class that provides the common properties and methods
 * for all types of tasks, such as TODOs, DEADLINEs, and EVENTs. Specific task
 * types will extend this class and implement the {@code getTypeIcon} method
 * to return their respective type icons.
 * </p>
 */
public abstract class Task {
    private final int taskID;
    private final String description;
    private boolean completed;

    /**
     * Constructs a Task with the specified ID and description.
     *
     * @param taskID      the unique identifier for the task.
     * @param description the description of the task.
     */
    public Task(int taskID, String description) {
        this.description = description;
        this.completed = false;
        this.taskID = taskID;
    }

    /**
     * Gets the unique identifier of the task.
     *
     * @return the task ID.
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Gets the description of the task.
     *
     * @return the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param newValue the new completion status of the task.
     */
    public void setCompleted(boolean newValue) {
        this.completed = newValue;
    }

    /**
     * Returns the type icon for this task.
     *
     * <p>
     * This is an abstract method that must be implemented by subclasses to
     * provide their specific type icons (e.g., "[T]" for TODOs, "[D]" for
     * DEADLINEs, "[E]" for EVENTs).
     * </p>
     *
     * @return the type icon of the task.
     */
    public abstract String getTypeIcon();
    // this function was suggested to me by Claude Sonnet as a way of making my code
    // more modular

    /**
     * Returns a string representation of this task.
     *
     * <p>
     * The string representation includes the task's type icon, completion status,
     * and description.
     * </p>
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        String tickBox = completed ? "[x]" : "[ ]";
        return getTypeIcon() + tickBox + " " + description;
    }
}
