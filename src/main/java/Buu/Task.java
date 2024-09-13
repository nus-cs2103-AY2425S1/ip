package Buu;

/**
 * Represents a task in the GPT application.
 * The Task class provides common functionality for all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int priority; // 1 = Low Priority, 2 = Medium Priority, 3 = High Priority

    /**
     * Constructs a new Task with the specified description and sets default priority to 1 (Low Priority).
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 1; // Default priority (Low Priority)
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Sets the priority level for the task.
     *
     * @param priority The priority level (1 for Low, 2 for Medium, 3 for High).
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority level of the task as a string.
     *
     * @return A string representing the priority level.
     */
    public String getPriorityLabel() {
        switch (priority) {
        case 1:
            return "Low Priority";
        case 2:
            return "Medium Priority";
        case 3:
            return "High Priority";
        default:
            return "None";
        }
    }
    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     * This method is intended to be overridden by subclasses.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileFormat();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (Priority: %s)",
                getTaskType(),
                isDone ? "X" : " ",
                description,
                getPriorityLabel());
    }

    /**
     * Get the task type for display in the task list.
     * Subclasses will override this to return the correct task type.
     *
     * @return A single letter representing the task type.
     */
    protected abstract String getTaskType();
}
