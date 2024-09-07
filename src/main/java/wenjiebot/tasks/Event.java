package wenjiebot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task in the wenjiebot application.
 * It extends the Task class and includes additional information for the event,
 * specifically the start and end times.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    protected LocalDateTime fromInDateTime;
    protected LocalDateTime toInDateTime;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.fromInDateTime = LocalDateTime.parse(from.trim(), formatter);
        this.toInDateTime = LocalDateTime.parse(to.trim(), formatter);
    }

    /**
     * Returns a string representation of the Event in a format suitable for storage.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for storage.
     */
    @Override
    public String toPrettierString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E" + super.toPrettierString() + "/from " + fromInDateTime.format(formatter)
                + " /to " + toInDateTime.format(formatter);
    }

    /**
     * Returns a string representation of the Event for display purposes.
     * The format includes the type of task, description, start time, and end time.
     *
     * @return A string representation of the Event for display.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm");
        return "[E]" + super.toString() + "(from: " + fromInDateTime.format(formatter)
                + " to: " + toInDateTime.format(formatter) + ")";
    }
}
