package main.java.angel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a due date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, HHmm");

    /**
     * Constructs a Deadline instance with the given description and due date/time.
     *
     * @param description Description of the task.
     * @param by          Due date/time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // Format the due date/time string with a comma
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toSaveFormat() {
        // Save format includes the date/time in yyyy/MM/dd HHmm format
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "D | " + super.toSaveFormat()
                + " | " + by.format(saveFormat);
    }
}
