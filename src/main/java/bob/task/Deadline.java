package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description,
                    LocalDateTime by,
                    boolean isDone,
                    LocalDateTime createdAt,
                    LocalDateTime completedAt) {
        super(description, isDone, createdAt, completedAt);
        this.by = by;
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 6 : "Number of elements after splitting should be 6.";
        return new Deadline(
                parameters[2],
                LocalDateTime.parse(parameters[3]),
                parameters[1].equals("1"),
                LocalDateTime.parse(parameters[4]),
                parameters[5].equals("null") ? null : LocalDateTime.parse(parameters[5]));

    }

    @Override
    public String toText() {
        return String.format(
                "D | %s | %s | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                this.by,
                super.getCreatedAt(),
                super.getCompletedAt());
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
    }

}
