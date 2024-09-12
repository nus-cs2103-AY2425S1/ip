package hue.task;

import hue.HueException;

/**
 * Represents a generic task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creates a {@code Task} with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Gets the status icon of the task, indicating whether it is done or not.
     *
     * @return A string representing the status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description ;
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
    public void unmarkDone() {
        this.isDone = false;
    }
    /**
     * Converts the task to a format suitable for saving to a file.
     *
     * @return A string representing the task in file format.
     */
    public String toFileFormat() {
        String task;
        if (this instanceof Todo) {
            task = "T";
        } else if (this instanceof Deadline) {
            task = "D";
        } else {
            task = "E";
        }
        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public void reschedule(String newDate) throws HueException {
        throw new HueException("Cannot rescheudle a task without a date!");
    }
}
