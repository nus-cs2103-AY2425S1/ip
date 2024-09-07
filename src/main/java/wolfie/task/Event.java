package wolfie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    /**
     * Returns the event task in the format to be saved in the file.
     * @return Event task in the format to be saved in the file.
     */
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
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event event)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(from, event.from) && Objects.equals(to, event.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to);
    }
}
