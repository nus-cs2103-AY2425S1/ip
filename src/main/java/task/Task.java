package task;

/**
 * Represents a task in the Friday application.
 */
public abstract class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Constructs a task with the specified task name that is not completed by default.
     *
     * @param taskName The task name.
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Constructs a task with the specified task name and completion status.
     *
     * @param taskName The task name.
     * @param isCompleted The completion status.
     */
    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the task name.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markAsUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns true if the task is completed, false otherwise.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the string representation of the task to be written to the file.
     *
     * @return The string representation of the task to be written to the file.
     */
    public abstract String writeToFile();

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskName;
    }
}
