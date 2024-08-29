package fanny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start and end time.
 * Inherits from the {@link Task} class.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime from;
    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} with the specified description, start time, and end time.
     *
     * @param description A description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    /**
     * Returns a string representation of the event, including its status,
     * description, start time, and end time.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + "(from: " + this.getFrom()
                + " to: " + this.getTo() + ")";
    }

    /**
     * Returns the formatted start time of the event.
     *
     * @return A string representing the start time in the format "MMM dd, yyyy HH:mm".
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }

    /**
     * Returns the formatted end time of the event.
     *
     * @return A string representing the end time in the format "MMM dd, yyyy HH:mm".
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }
}
