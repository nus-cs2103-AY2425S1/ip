package hien.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that includes a description and a time range.
 * Inherits from the {@code Task} class and adds a start and end date/time to the task.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     * The string includes the status icon, description, and the formatted start and end date/time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_DATE_FORMAT)
                                        + " to: " + to.format(OUTPUT_DATE_FORMAT) + ")";
    }
}