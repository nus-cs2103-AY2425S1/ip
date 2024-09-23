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
     * Constructs a new Event with the default priority (low).
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
     * Constructs a new Event with a specific priority.
     *
     * @param description The description of the event task.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     * @param priority The priority level (1 for high, 2 for medium, 3 for low).
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, int priority) {
        super(description);
        this.from = from;
        this.to = to;
        setPriority(priority); // Set the priority using the method from the superclass.
    }

    /**
     * Returns a string representation of the event, including its description, priority,
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
     * description, start and end dates formatted as "d/M/yyyy HHmm", and priority.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s | %d",
                (isDone ? 1 : 0),
                description,
                from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                getPriority());
    }
}
