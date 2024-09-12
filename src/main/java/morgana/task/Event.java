package morgana.task;

import static morgana.util.DateTimeUtil.COMPACT_FORMATTER;
import static morgana.util.DateTimeUtil.VERBOSE_FORMATTER;

import java.time.LocalDateTime;

/**
 * Represents a task with a description that spans a specific period of time.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an {@code Event} task with the specified description,
     * start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileFormat() {
        return "%s | %s | %s".formatted(
                super.toFileFormat(),
                start.format(COMPACT_FORMATTER),
                end.format(COMPACT_FORMATTER));
    }

    @Override
    public String toString() {
        return "%s (from: %s to: %s)".formatted(
                super.toString(),
                start.format(VERBOSE_FORMATTER),
                end.format(VERBOSE_FORMATTER));
    }
}
