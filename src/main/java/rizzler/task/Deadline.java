package rizzler.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with the addition of a <code>deadlineTime</code>
 * that it should be finished by.
 */
public class Deadline extends Task {
    private LocalDate deadlineTime;

    /**
     * Create a <code>Deadline</code> object.
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     */
    Deadline(String deadlineDesc, String deadlineTime) {
        this(deadlineDesc, deadlineTime, false);
    }

    Deadline(String deadlineDesc, String deadlineTime, boolean isDone) throws DateTimeParseException {
        super(deadlineDesc, isDone);
        this.deadlineTime = DateTimeParser.to_datetime(deadlineTime);
    }

    /**
     * Generates a string ready for printing as a description of a <code>Deadline</code>.
     * This includes the <code>Deadline</code> description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.to_str(this.deadlineTime) + ")";
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + this.deadlineTime;
    }
}
