package echobot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event task with a start and end date.
 * Inherits from the Task class and includes the start and end dates for the event.
 */
public class Event extends Task {
    public LocalDate from;
    public LocalDate to;

    /**
     * Constructs an Event task with a description, start date, and end date.
     *
     * @param description A brief description of the task.
     * @param from A string representing the start date of the event in various possible formats.
     * @param to A string representing the end date of the event in various possible formats.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDate(from); // Parse the string into a LocalDate
        this.to = parseDate(to);
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
     * Returns a string representation of the Event task, including its description, start date, and end date.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a file.
     *
     * @return A string representing the Event task in a file-compatible format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }
}
