package qwerty.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates a Deadline type task.
 * A Deadline contains a time by which it is due.
 */
public class Deadline extends Task {

    /** The deadline of the task */
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline as a formatted string.
     * The format is "MMM dd yyyy HHmm", e.g. "Aug 26 2024 1450".
     *
     * @return Formatted string representing the deadline.
     */
    public String getDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "D",
                getStatusIcon(),
                getDescription(),
                by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
        );
    }
}
