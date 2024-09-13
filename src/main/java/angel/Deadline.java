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
     * @throws NullPointerException If by is null.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        if (by == null) {
            throw new NullPointerException("Deadline cannot be null.");
        }
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline in a user-readable format.
     *
     * @return A string representation of the Deadline, including its status, description, and due date/time.
     */
    @Override
    public String toString() {
        // Format the due date/time string with a comma
        return "[D][" + getStatusIcon() + "] " + description
                + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns a string representation of the Deadline in a format suitable for saving to a file.
     *
     * @return A string representation of the Deadline in the save format,
     * including its type, status, description, and due date/time.
     */
    @Override
    public String toSaveFormat() {
        // Save format includes the date/time in yyyy/MM/dd HHmm format
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return "D | " + super.toSaveFormat()
                + " | " + by.format(saveFormat);
    }
}
