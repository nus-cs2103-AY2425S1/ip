package morgana.task;

import static morgana.util.DateTimeUtil.COMPACT_FORMATTER;
import static morgana.util.DateTimeUtil.VERBOSE_FORMATTER;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a due date.
 */
public class Deadline extends Task {
    private final LocalDateTime dueDate;

    /**
     * Constructs a {@code Deadline} with the specified description and due date.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(TaskType.DEADLINE, description);
        this.dueDate = dueDate;
    }

    @Override
    public String toFileFormat() {
        return "%s | %s".formatted(super.toFileFormat(), dueDate.format(COMPACT_FORMATTER));
    }

    @Override
    public String toString() {
        return "%s (by: %s)".formatted(super.toString(), dueDate.format(VERBOSE_FORMATTER));
    }
}
