package shenhe.task;

import java.time.LocalDateTime;

import shenhe.parser.DateParser;

/**
 * Represents a deadline task with a specific due date and time.
 * This class extends {@link Task} to include a deadline date and time.
 */
public class Deadline extends Task {

    /** The deadline date and time for the task. */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description, status, and deadline date and time.
     *
     * @param description the description of the task
     * @param isDone      the status of the task (true if done, false otherwise)
     * @param by          the deadline date and time for the task
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for saving to a file.
     * The format includes the task type ("D"), status icon, description, and the deadline date and time.
     *
     * @return a string representation of the deadline task in file format
     */
    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + DateParser.format(by);
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the task type, status icon, description, and the formatted deadline date and time.
     *
     * @return a string representation of the deadline task, or null if the deadline is not set
     */
    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + DateParser.format(by) + ")";
        }
        return null;
    }
}
