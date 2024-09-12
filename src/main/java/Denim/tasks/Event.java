package denim.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with two additional LocalDateTime from and to.
 * This class provides methods to manage and retrieve the event's details.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the specified description and duration.
     * This event is initially marked as not done.
     *
     * @param description The description of the event.
     * @param from The start time and date of this event.
     * @param to The end time and date of this event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event with the specified description and duration.
     *
     * @param description The description of the event.
     * @param isDone The completion status of this event.
     * @param from The start time and date of this event.
     * @param to The end time and date of this event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Event.DATE_TIME_FORMATTER)
                + " to: " + to.format(Event.DATE_TIME_FORMATTER) + ")";
    }

    @Override
    public String toSimplifiedString() {
        String formattedString = String.format("E | %d | %s | %s | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription(), from.format(Event.DATE_TIME_FORMATTER), to.format(Event.DATE_TIME_FORMATTER));
        return formattedString;
    }
}

