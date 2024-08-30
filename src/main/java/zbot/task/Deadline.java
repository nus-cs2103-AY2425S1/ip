package zbot.task;

import java.time.LocalDateTime;

import zbot.Parser;

/**
 * Represents a deadline with a description and due datetime.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline.
     * @param by          Due date of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due datetime of the deadline.
     *
     * @return Due datetime of the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatDateTimeToOutput(by));
    }
}
