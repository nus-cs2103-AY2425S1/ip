package elysia.tasks;

/**
 * Represents a general task with a description and a status indicating whether it is completed.
 * Provides basic functionality to update the task's status and format it as a string for display or file storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates the status of the task to mark it as done or not done.
     *
     * @param status A boolean value where true marks the task as done, and false marks it as not done.
     */
    public void updateStatus(boolean status) {
        isDone = status;
    }

    /**
     * Converts the Task to a string format suitable for saving to a file.
     * The format includes the completion status (1 for done, 0 for not done) and the task description.
     *
     * @return A string representation of the Task in file format.
     */
    public String toFile() {
        return "|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Returns a string representation of the Task, including its status and description.
     * The format is "[X]" for a completed task or "[ ]" for an incomplete task, followed by the description.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
