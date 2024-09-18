package assistinator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents event task.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Initialises an event task.
     * @param description Task description.
     * @param start Start time.
     * @param end End time.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (from: %s to: %s)", start.format(formatter), end.format(formatter));
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        return String.format(
                "E | %s | %s | %s | %s",
                isDone ? TaskStatus.DONE : TaskStatus.NOTDONE,
                description,
                start.format(formatter),
                end.format(formatter)
        );
    }

    public LocalDateTime getStartTime() {
        return start;
    }

    public LocalDateTime getEndTime() {
        return end;
    }
}
