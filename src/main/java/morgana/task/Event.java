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
     * Constructs an {@code Event} with the specified description and time period.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(TaskType.EVENT, description);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
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
