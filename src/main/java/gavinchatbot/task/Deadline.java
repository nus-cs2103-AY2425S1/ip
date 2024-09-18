package gavinchatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date in the format yyyy-MM-dd.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        // to ensure no leading/trailing spaces
        by = by.trim();
        if (!by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format, expected yyyy-MM-dd");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Returns the string representation of the task in the format used for saving to a file.
     *
     * @return The string representation of the task in save format.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }

    /**
     * Returns the string representation of the task, including the deadline date.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}
