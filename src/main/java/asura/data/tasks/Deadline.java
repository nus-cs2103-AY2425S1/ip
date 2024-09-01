package asura.data.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline with the specified description and date due.
     * @param description The description of the deadline.
     * @param by The date that the deadline is due by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss a")) + ")";
    }
}
