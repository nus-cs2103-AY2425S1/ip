package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with specific start and end dates and times.
 * Extends {@link Task}.
 */
public class Event extends Task {
    /**
     * The starting date and time of the event.
     */
    private LocalDateTime from;

    /**
     * The ending date and time of the event.
     */
    private LocalDateTime to;

    /**
     * Constructs a new Event.
     *
     * @param description The description of the event task.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event, including its description,
     * start and end dates formatted as "MMM d yyyy h:mma".
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }

    /**
     * Returns a string suitable for file storage, representing the event task with its completion status,
     * description, start and end dates formatted as "d/M/yyyy HHmm".
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", (isDone ? 1 : 0),
                description,
                from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
