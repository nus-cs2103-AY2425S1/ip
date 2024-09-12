package eevee;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task that has a deadline.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT = "MMM d yyyy";
    protected String by;

    /**
     * Constructs a task with the given description and deadline.
     *
     * @param description The String description of the task.
     * @param by The Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description.trim());
        this.by = formatDueDate(by.trim());
    }

    /**
     * Formats a date given in the form of yyyy-mm-dd to mmm d yyyy.
     * Does not change due date inputs in other formats.
     *
     * @param dueDate The String input for due date.
     * @return The correctly formatted due date. e
     */
    private String formatDueDate(String dueDate) {
        try {
            LocalDate date = LocalDate.parse(dueDate);
            return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeException ignored) {
            return dueDate;  // Return the original string if the date is invalid
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + by;
    }
}

