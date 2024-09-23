package xbot.task;

import xbot.parser.Parser;

/**
 * Represents a task with a deadline in the XBot application.
 * A {@code Deadline} task has a description and a deadline by which it should be completed.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.D);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline by which the task should be completed as a String.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.changeDateFormat(by) + ")";
    }
}
