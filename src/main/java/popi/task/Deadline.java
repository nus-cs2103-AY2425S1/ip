package popi.task;

import java.time.LocalDateTime;

import popi.utils.DateTimeUtils;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline.
     * @param deadline End time of the deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtils.formatDateTime(deadline) + ")";
    }

    @Override
    protected String getType() {
        return "D";
    }

    @Override
    public String toDataString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | "
                + getDescription() + " | " + deadline;
    }
}
