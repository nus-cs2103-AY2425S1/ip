package citadel.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Citadel application.
 * An event has a description, a start time, and an end time.
 * This class extends {@link Task}.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event, including its type, status icon,
     * description, and the start and end times formatted as "from: [start time] to: [end time]".
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.formatTime(this.from)
                + " to: " + this.formatTime(this.to) + ")";
    }

    /**
     * Returns a string representation of the event for printing or saving,
     * including its type, status icon, description, and the start and end times
     * formatted as "from: [start time] to: [end time]".
     *
     * @return A string representation of the event for printing or saving.
     */
    @Override
    public String printTask() {
        return "[E]" + super.toString() + " (from: " + this.from
                .format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"))
                + " to: " + this.to
                .format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm")) + ")";
    }

    /**
     * Formats the given {@code LocalDateTime} object into a string using the pattern "dd/MM/yyyy HH:mm".
     *
     * @param time The time to be formatted.
     * @return A string representation of the formatted time.
     */
    private String formatTime(LocalDateTime time) {
        return time
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

