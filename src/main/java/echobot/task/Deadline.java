package echobot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a deadline task with a specific due date.
 * Inherits from the Task class and includes the due date for the task.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description A brief description of the task.
     * @param by A string representing the due date of the task in various possible formats.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDate(by);
    }

    /**
     * Parses a date string into a LocalDate object.
     * Supports multiple date formats.
     *
     * @param date A string representing the date.
     * @return The parsed LocalDate object.
     * @throws IllegalArgumentException If the date format is not supported.
     */
    private LocalDate parseDate(String date) {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-d"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));

        // Add more patterns as needed

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying other patterns
            }
        }

        throw new IllegalArgumentException("Date format not supported: " + date);
    }

    /**
     * Returns a string representation of the Deadline task, including its description and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving to a file.
     *
     * @return A string representing the Deadline task in a file-compatible format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    public LocalDate getBy() {
        return this.by;
    }
}
