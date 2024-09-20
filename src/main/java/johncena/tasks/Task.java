package johncena.tasks;

import java.time.LocalDate;

/**
 * Represents a task.
 * A tasks.Task object corresponds to a task represented by a description and a completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for tasks.Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
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
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done.
     *
     * @return Completion status of the task.
     */
    public boolean isTaskDone() {
        return this.isDone;
    }

    /**
     * Checks if the task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the given date, false otherwise.
     */
    public boolean occursOn(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
