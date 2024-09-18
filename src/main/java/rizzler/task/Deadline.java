package rizzler.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import rizzler.ui.parser.DateTimeParser;

/**
 * Represents a Task with the addition of a <code>deadlineTime</code>
 * that it should be finished by.
 */
public class Deadline extends Task {
    private final String deadlineTime;

    /**
     * Constructor for a <code>Deadline</code> object.
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     * @throws DateTimeParseException If the user entered deadlineTime is of the wrong format.
     */
    public Deadline(String deadlineDesc, String deadlineTime) {
        this(deadlineDesc, deadlineTime, false);
    }

    /**
     * Constructor for a <code>Deadline</code> object.
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     * @param isDone Boolean to indicate if the task being created has been marked as done.
     * @throws DateTimeParseException If the user entered deadlineTime is of the wrong format.
     */
    public Deadline(String deadlineDesc, String deadlineTime, boolean isDone) {
        super(deadlineDesc, isDone);
        if (DateTimeParser.canParse(deadlineTime)) {
            LocalDate deadlineDatetime = LocalDate.parse(deadlineTime);
            this.deadlineTime = DateTimeParser.toStr(deadlineDatetime);
        } else {
            this.deadlineTime = deadlineTime;
        }
    }

    /**
     * Generates a string ready for printing as a description of a <code>Deadline</code>.
     * This includes the <code>Deadline</code> description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineTime + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + this.deadlineTime;
    }
}
