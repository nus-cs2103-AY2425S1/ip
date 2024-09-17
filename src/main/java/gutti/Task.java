package gutti;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a completion status.
 * The task can be marked as done or not done.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The completion status of the task. True if the task is done, false otherwise. is false by default.
     */
    protected boolean isDone;

    protected LocalDateTime completionDate;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.completionDate = null;
    }
    public Task(String description, boolean isDone, LocalDateTime markedTime) {
        this.description = description;
        this.isDone = isDone;
        this.completionDate = markedTime;
    }

    /**
     * Returns the status icon of the task.
     * "X" if the task is done, a space otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public String markAsDone() {
        this.isDone = true;
        StringBuilder sb = new StringBuilder();
        this.completionDate = LocalDateTime.now();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(this.toString());
        return sb.toString();
    }

    /**
     * Unmarks the task as not done and prints a confirmation message.
     */
    public String unmark() {
        this.isDone = false;
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n" );
        this.completionDate = null;
        sb.append(this.toString());
        return sb.toString();

    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
