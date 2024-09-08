package alice.task;

import java.util.Objects;

/**
 * Represents a task with a description and a completion status.
 * Provides methods to mark the task as done or undone, and to get a string representation of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task's status icon.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     * Includes the task's status and description.
     *
     * @return A string in the format " | status | description", where status is "1" if done, "0" otherwise.
     */
    public String saveString() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Returns a string representation of the task for display purposes.
     * Includes the task's status icon and description.
     *
     * @return A string in the format "[statusIcon] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
