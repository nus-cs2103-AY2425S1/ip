package Tasks;

import enums.TaskType;

/**
 * Represents a generic task with a description and completion status.
 * This base class is extended by more specific task types that include additional properties and behaviors.
 */
public class Task {
    protected String description;  // Textual description of the task.
    protected boolean isDone;      // Completion status of the task.
    protected TaskType taskType;   // Type of the task, as defined in the TaskType enum.

    /**
     * Constructs a new Task with a specified description and type.
     *
     * @param description The textual description of the task.
     * @param taskType The type of the task, indicating its category (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns a status icon representing the task's completion status.
     * '[X]' indicates the task is completed, and '[ ]' indicates it is not.
     *
     * @return A string containing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Updates the description of the task.
     *
     * @param description The new description to set for the task.
     */
    public void getDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The taskType enum value representing the category of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Provides a string representation of the task, including its completion status and description.
     *
     * @return A string detailing the task's status and description.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns a formatted string suitable for file storage, including the task's completion status and description.
     *
     * @return A string formatted for file storage.
     */
    public String toFileFormat() {
        String marked = isDone ? "1" : "0";
        return " | " + marked + " | " + description;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkDone() {
        isDone = false;
    }
}
