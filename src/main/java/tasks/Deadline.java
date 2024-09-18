package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class to store attributes and methods.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs Deadline object.
     *
     * @param description Description of the Deadline Task.
     * @param by When to finish the task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
