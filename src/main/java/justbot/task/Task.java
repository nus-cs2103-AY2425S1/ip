package justbot.task;

/**
 * Represents an abstract task in the Justbot application.
 * Each task has a description and a status indicating whether it is done or not.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and " " (a space) indicates that the task is not done.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getTaskDescription() {

        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the completion status of the task as a string.
     * "X" indicates that the task is done, and "0" indicates that the task is not done.
     *
     * @return A string representing the completion status of the task.
     */
    public String getIsDoneString() {
        if (this.isDone) {
            return "X";
        } else {
            return "0";
        }
    }

    /**
     * Sets the completion status of the task.
     *
     * @param bool {@code true} to mark the task as done, {@code false} to mark it as not done.
     */
    public void setIsDone(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the task description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDescription();
    }
}
