package skd.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, FORMATTER);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}