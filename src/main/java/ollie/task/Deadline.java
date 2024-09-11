package ollie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. It contains a date which represents
 * when the task is due by.
 */
public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the formatted string for easy parsing and writing into file (database).
     *
     * @return Formatted String.
     */
    public String getFormattedString() {
        return "D | " + super.getFormattedString() + " | " + this.by;
    }
}