package michaelscott.task;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

/**
 * Represents an event task in the MichaelScott.MichaelScott task-tracking program.
 * An event has a start time and an end time in addition to a description.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the description of the event.
     *
     * @return The description as a String.
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event as a String object.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ")
                + desc + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the In-file representation of the event.
     *
     * @return String in-file representation of the event as a String object.
     */
    @Override
    public String toFile() {
        return "E | " + (isDone ? "1" : "0") + " | "
                + desc + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }
}
