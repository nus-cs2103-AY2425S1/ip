package blacknut.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * Represents a task with a deadline in the Blacknut application.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and a deadline date/time.
     *
     * @param description The description of the task.
     * @param by The deadline date/time in the format yyyy-MM-dd HH:mm.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * Returns the LocalDate of the deadline for comparison.
     *
     * @return The LocalDate of the deadline.
     */
    public LocalDate getDate() {
        return by.toLocalDate();
    }

    /**
     * Converts the Deadline task to a string format suitable for saving to a file.
     *
     * @return The string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return super.toFileFormat() + " | " + by.format(outputFormatter);
    }


    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string in the format "[D][status] description (by: MMM dd yyyy, HH:mm)".
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }
}
