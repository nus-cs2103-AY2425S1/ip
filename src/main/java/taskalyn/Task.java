package taskalyn;

/**
 * Abstract class for Task.
 */
public abstract class Task {
    private String taskItem;
    private boolean isCompleted;

    /**
     * Constructs the Task object with description and completion status.
     *
     * @param taskItem String for task description.
     * @param isCompleted Whether a task is completed or not.
     */
    public Task(String taskItem, boolean isCompleted) {
        this.taskItem = taskItem;
        this.isCompleted = false;
    }

    /**
     * Checks if a task is completed or not.
     *
     * @return {@code true} if task is completed. {@code false} if not.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets a task as complete.
     */
    public void setComplete() {
        this.isCompleted = true;
    }

    /**
     * Sets a task as incomplete.
     */
    public void setIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns the task description.
     *
     * @return String of task description.
     */
    public String getTaskDescription() {
        return taskItem;
    }

    /**
     * Returns a String expression of the Task.
     *
     * @return String expression of task.
     */
    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.taskItem;
    }

    /**
     * Returns a String expression to be used in database file.
     *
     * @return String expression used in database file.
     */
    public abstract String toDatabaseFormat();
}
