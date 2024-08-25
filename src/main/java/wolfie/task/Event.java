package wolfie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @param isDone Whether the event is done.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        // store the start time
        this.from = from;
        this.to = to; // store the end time
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | "
                + from.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " | "
                + to.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
