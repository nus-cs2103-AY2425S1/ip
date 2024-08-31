package garfield.tasks;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or undone, retrieve the task description,
 * and obtain string representations of the task for display and saving.
 */
public abstract class Task {
    private String taskDescription;
    private boolean doneStatus;

    /**
     * Constructs a new Task with the specified description.
     * The task is initialized as not done.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.doneStatus = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.doneStatus = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsUndone() {
        this.doneStatus = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns a string representation of the task suitable for display to the user.
     * The representation includes the completion status and task description.
     *
     * @return A string representation of the task, with "[X]" indicating completion
     *         and "[ ]" indicating incompleteness.
     */
    @Override
    public String toString() {
        if (this.doneStatus) {
            return "[X] " + taskDescription;
        } else {
            return "[ ] " + taskDescription;
        }
    }

    /**
     * Returns a string representation of the task suitable for saving to storage.
     * The representation includes a flag indicating completion and the task description.
     *
     * @return A string representation of the task, with "1 | " indicating completion
     *         and "0 | " indicating incompleteness.
     */
    public String toSaveRepresentation() {
        if (this.doneStatus) {
            return "1 | " + taskDescription;
        } else {
            return "0 | " + taskDescription;
        }
    }
}
