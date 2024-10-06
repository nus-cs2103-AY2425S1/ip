package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with the given description, start time, and end time.
     *
     * @param description The event description.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event, including its status, description,
     * start time, and end time.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMATTER)
                + " to: " + to.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * Returns the string format of the event for saving to file.
     *
     * @return A formatted string representing the event for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from.format(SAVE_FORMATTER)
                + " | " + to.format(SAVE_FORMATTER);
    }
}
