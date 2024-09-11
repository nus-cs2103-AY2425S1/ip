package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task in the DailyTasks application.
 * A task contains a description and a status indicating whether it is done or not.
 */
public class Task {

    /** The description of the task. */
    protected String description;
    /** The status of the task, indicating whether it is done or not. */
    protected boolean isDone;
    /** The priority of the task. */
    protected int priority;

    /**
     * Creates a new task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     * An "X" indicates the task is done, and a blank space indicates it is not done.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the priority of the task.
     *
     * @return The priority of the task.
     */
    public int getPriority() {
        return this.priority;
    }


    /**
     * Checks if the task is occurring at a specified date and time.
     * By default, this method returns false and is overridden by specific task types such as Event and Deadline.
     *
     * @param taskDate The date and time to check.
     * @return false by default.
     */
    public boolean occurring(LocalDateTime taskDate) {
        return false; // Default to null, overridden by Deadline and Event
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Sets the priority of the task
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns a string representation of the task, which is the task description.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
