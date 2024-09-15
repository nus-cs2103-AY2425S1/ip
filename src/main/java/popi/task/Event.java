package popi.task;

import java.time.LocalDateTime;

import popi.utils.DateTimeUtils;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for Event.
     * @param description Description of the event.
     * @param start Start time of the event.
     * @param end End time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, start);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtils.formatDateTime(start)
                + " to: " + DateTimeUtils.formatDateTime(end) + ")";
    }

    @Override
    protected String getType() {
        return "E";
    }

    @Override
    public String toDataString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription()
                + " | " + start + " | " + end;
    }
}
