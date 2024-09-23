package rizzler.task;

import rizzler.parser.DateTimeParser;

/**
 * Represents a Task with the addition of a <code>deadlineTime</code>
 * that it should be finished by.
 */
public class Deadline extends Task {
    public static final String TYPE_STRING = "Deadline";
    private final String deadlineTime;

    /**
     * Constructs a <code>Deadline</code> object.
     *
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     */
    public Deadline(String deadlineDesc, String deadlineTime) {
        this(deadlineDesc, deadlineTime, false);
    }

    /**
     * Constructs a <code>Deadline</code> object.
     *
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     * @param isDone Boolean to indicate if the task being created has been marked as done.
     */
    public Deadline(String deadlineDesc, String deadlineTime, boolean isDone) {
        super(deadlineDesc, isDone);
        this.deadlineTime = DateTimeParser.toStr(deadlineTime);

    }

    /**
     * Generates a string ready for printing as a description of a <code>Deadline</code>.
     * This includes the <code>Deadline</code> description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getType() {
        return TYPE_STRING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + this.deadlineTime;
    }
}
