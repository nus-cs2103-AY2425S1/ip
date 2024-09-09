package bao.task;

import java.time.LocalDateTime;

import bao.main.Bao;

/**
 * The Event class represents a task that occurs within a specific period of time.
 * It extends the Task class by adding start and end times on top of the description of the task.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from LocalDateTime of when the event starts.
     * @param to LocalDateTime of when the event ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from != null : "From date and time should not be null";
        assert to != null : "To date and time should not be null";
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFromDateTime() {
        return from;
    }

    public LocalDateTime getToDateTime() {
        return to;
    }

    /**
     * Returns a string representation of the event task, prefixed with "E" to indicate
     * it is an event task, and includes the formatted start and end dates and times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | "
                + from.format(Bao.getOutputDateFormat()) + "-" + to.format(Bao.getOutputDateFormat())
                + getTagsAsString();
    }

    /**
     * Returns a string representation of the event task for saving, prefixed with "E" to indicate
     * it is an event task, and includes the formatted start and end dates and times.
     *
     * @return String representation of the event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description.trim()
                + " | " + from.format(Bao.getFileDateFormat()) + " - " + to.format(Bao.getFileDateFormat())
                + getTagsAsString();
    }
}
