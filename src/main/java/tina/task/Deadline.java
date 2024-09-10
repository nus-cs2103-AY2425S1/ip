package tina.task;

import java.time.LocalDateTime;

import tina.Parser;


/**
 * Represents a task with a deadline.
 * A <code>Deadline</code> object contains a string description, a boolean to mark completion,
 * and the date/time by which the task should be completed.
 */

public class Deadline extends Task {
    private final LocalDateTime end;

    /**
     * Constructs a new <code>Deadline</code> object with the specified description and end time when parsed from user.
     *
     * @param des The description of the task.
     * @param end The end time of the task, in a parsable date-time string format.
     */

    public Deadline(String des, String end) {
        super(des);
        this.end = Parser.parseDate(end);
    }

    /**
     * Constructs a new <code>Deadline</code> object with the specified description,
     * completion status, and end time, when parsed from storage.
     *
     * @param des The description of the task.
     * @param isMark The completion status of the task.
     * @param end The end time of the task, in a parsable date-time string format.
     */

    public Deadline(String des, boolean isMark, String end) {
        super(des);
        this.end = LocalDateTime.parse(end);
        this.isMark = isMark;
    }

    /**
     * Returns the description of the task, including its type and formatted end time.
     *
     * @return A string of the task description.
     */
    @Override
    public String getDes() {
        return "[D]" + super.getDes() + " (by: " + Parser.formatDate(end) + ")";
    }

    /**
     * Returns a string representation of the task for storage purposes.
     *
     * @return A string representing the task in a format for saving to a file.
     */

    @Override
    public String toString() {
        return String.format("D %d %s | %s", isMark ? 1 : 0, des, end);
    }
}
