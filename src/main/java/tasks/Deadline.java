package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the given description and due date.
     *
     * @param description The deadline description.
     * @param by The date and time the deadline is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline, including its status, description,
     * and due date.
     *
     * @return A formatted string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * Returns the string format of the deadline for saving to file.
     *
     * @return A formatted string representing the deadline for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by.format(SAVE_FORMATTER);
    }
}
