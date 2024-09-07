package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task which has a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * The start time of the event.
     */
    protected LocalDateTime startTime;

    /**
     * The end time of the event.
     */
    protected LocalDateTime endTime;

    /**
     * Creates an event task with the given description, start time and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.startTime = from;
        this.endTime = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(FORMATTER)
                + " to: " + endTime.format(FORMATTER) + ")";
    }
}
