package elon.task;

import elon.task.Task;

/**
 * Represents a ToDo task, which is a specific type of Task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description and status.
     *
     * @param description the description of the ToDo task
     * @param isDone whether the task is completed or not
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format is "[T][statusIcon] description", where statusIcon is "X" if the task is done,
     * or a space " " if not done, and description is the description of the task
     *
     * @return the string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string suitable for saving to a file, representing the ToDo task.
     * The format is "T | status | description", where status is "1" if the task is done, or "0" if not done,
     * and description is the description of the task.
     *
     * @return the string representation of the ToDo task for saving to a file
     */
    @Override
    public String toFileString() {
        return "T | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString();
    }
}