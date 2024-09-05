package niko.task;

import java.util.Date;

/**
 * Represents a task with a description and status.
 * Tasks can be of various types and have different statuses (e.g., done or not done).
 */
public class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType type;
    protected Date date;

    /**
     * Constructs a Task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.status = TaskStatus.NOT_DONE;
        this.type = type;
    }

    /**
     * Sets the date associated with the task.
     *
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the date associated with the task.
     *
     * @return The date as a Date object.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (status == TaskStatus.DONE ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.status = TaskStatus.NOT_DONE;
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
