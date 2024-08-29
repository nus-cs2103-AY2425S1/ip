package Jard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start, and end.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event for file storage.
     *
     * @return A string representing the event task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string for the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
