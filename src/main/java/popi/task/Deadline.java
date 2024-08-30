package popi.task;

import popi.utils.DateTimeUtils;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime end;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline.
     * @param end End time of the deadline.
     */
    public Deadline(String description, LocalDateTime end) {
        super(description, end);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtils.formatDateTime(end) + ")";
    }

    @Override
    protected String getType() {
        return "D";
    }

    @Override
    public String toDataString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | "
                + this.description + " | " + end;
    }
}
