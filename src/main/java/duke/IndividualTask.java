package duke;

/**
 * Represents an abstract task with a description and a status indicating whether the task is done.
 * This class is intended to be extended by specific types of tasks such as
 * {@code ToDo}, {@code Deadline}, and {@code Event}.
 */
public abstract class IndividualTask {

    private String taskDescription;
    private boolean isTaskDone;

    /**
     * Constructs an {@code IndividualTask} with the specified task description.
     *
     * @param taskDescription The description of the task.
     */
    public IndividualTask(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    /**
     * Returns an icon representing the completion status of the task.
     * An "X" is returned if the task is done; otherwise, an empty string is returned.
     *
     * @return A string representing the task's completion status.
     */
    public String getIcon() {
        return this.isTaskDone ? "X" : "";
    }

    /**
     * Returns a save icon representing the completion status of the task.
     * "1" is returned if the task is done; otherwise, "0" is returned.
     *
     * @return A string representing the task's completion status for saving to a file.
     */
    public String getSaveIcon() {
        return this.isTaskDone ? "1" : "0";
    }

    /**
     * Marks the task as done or not done based on the provided message.
     * If the message is "mark", the task is marked as done; otherwise, it is marked as not done.
     *
     * @param msg A string indicating whether to mark or unmark the task.
     */
    public void markOrUnmark(String msg) {
        this.isTaskDone = "mark".equals(msg);
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return The formatted string for saving the task to a file.
     */
    public abstract String saveToFileFormat();

    /**
     * Returns a string representation of the task, including its completion status and description.
     * If the task is done, an "X" is included in the representation; otherwise, an empty box is shown.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isTaskDone ? "X" : " ") + "] " + this.getTaskDescription();
    }
}
