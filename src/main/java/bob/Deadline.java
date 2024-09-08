package bob;

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

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 4 : "Number of elements after splitting should be 4.";
        return new Deadline(
                parameters[2],
                LocalDateTime.parse(parameters[3]),
                parameters[1].equals("1"));

    }

    @Override
    public String toText() {
        return String.format(
                "D | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                this.by);
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
    }

}
