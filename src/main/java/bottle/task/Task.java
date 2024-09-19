package bottle.task;

/**
 * Represents a generic task with a description and a completion status.
 * This class serves as an abstract base class for specific types of tasks.
 */
public abstract class Task {

    /**
     * Indicates whether the task is checked (completed).
     */
    protected boolean isChecked;

    /**
     * The description of the task.
     */
    protected String taskDesc;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param taskDesc the description of the task
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isChecked = false; // Task is initially unchecked
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the description of the task
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isChecked = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unMark() {
        this.isChecked = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return a string representation of the task in the format "[X] description" if completed, or "[ ] description" if not
     */
    @Override
    public String toString() {
        String box = isChecked ? "[X] " : "[ ] ";
        return box + taskDesc;
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     * This method must be implemented by subclasses to provide the appropriate format.
     *
     * @return a string representation of the task in a file-friendly format
     */
    public abstract String toSaveFormat();
}
