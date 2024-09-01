package morgana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "%s | %s".formatted(super.toFileFormat(), by.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "%s (by: %s)".formatted(super.toString(), by.format(formatter));
    }
}
