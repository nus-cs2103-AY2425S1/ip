package jeff.task;

import java.time.LocalDateTime;

import jeff.Storage;

/**
 * Represents an Event task with a description, start time, and end time.
 *
 * An Event object stores information about a task that occurs between two specific dates and times.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param task Description of the event.
     * @param from Start date and time of the event.
     * @param to End date and time of the event.
     */
    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String fromDateToString() {
        return from.format(Storage.getDateTimeFormatter());
    }

    public String toDateToString() {
        return to.format(Storage.getDateTimeFormatter());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateToString() + " to: " + this.toDateToString() + ")";
    }

    @Override
    public String saveAsCsv() {
        return "E," + super.saveAsCsv() + "," + this.fromDateToString() + "," + this.toDateToString();
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}
