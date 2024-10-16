package michaelscott.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime deadlineDate;

    /**
     * Constructs a deadline.
     *
     * @Params String description, deadline date
     */
    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public String getDescription() {
        return desc;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + this.deadlineDate.format(FORMATTER) + ")";
    }

    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + this.deadlineDate.format(FORMATTER);
    }
}
