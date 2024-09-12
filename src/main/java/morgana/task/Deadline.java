package morgana.task;

import static morgana.util.DateTimeUtil.COMPACT_FORMATTER;
import static morgana.util.DateTimeUtil.VERBOSE_FORMATTER;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "%s | %s".formatted(super.toFileFormat(), by.format(COMPACT_FORMATTER));
    }

    @Override
    public String toString() {
        return "%s (by: %s)".formatted(super.toString(), by.format(VERBOSE_FORMATTER));
    }
}
