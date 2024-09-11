package susan.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end time.
 * This class is 1 of 3 types of tasks in the Susan task management application.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"))
            + " to: "
            + to.format(DateTimeFormatter.ofPattern("h:mm a"))
            + ")";
    }
}
