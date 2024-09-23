package nebula.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an Event task with the specified description, start time, and end time
     *
     * @param description The description of the task provided by the user
     * @param start The start time of the task provided by the user
     * @param end The end time of the task provided by the user
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDateTimeOrDate(start);
        this.end = parseDateTimeOrDate(end);
    }

    /**
     * Parses the input date string and returns a LocalDateTime object. If it
     * contains only a date without time, appends "00:00" to default the
     * time to midnight.
     *
     * @param dateStr The start or end date of the task provided by the user
     * @return A LocalDateTime object to be assigned to the start and end fields
     */
    private LocalDateTime parseDateTimeOrDate(String dateStr) {
        assert dateStr != null && !dateStr.isEmpty() : "Date string cannot be null or empty";

        try {
            // Try parsing with date and time
            return LocalDateTime.parse(dateStr, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            try {
                // If parsing without time, append "T00:00" to default the time to midnight
                return LocalDateTime.parse(dateStr + " 00:00", DATE_TIME_FORMAT);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Invalid date format: " + dateStr);
            }
        }
    }

    /**
     * Returns a string representation of the Event object.
     * The string includes a "[E]" prefix to indicate that this is an Event task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[E] <super.toString()>"
     */
    @Override
    public String toString() {
        return this.getTaskSymbol() + " " + super.toString()
                + " (from: " + getStart() + " to: " + getEnd() + ")";
    }

    /**
     * Returns the taskSymbol for the Event object
     *
     * @return A string with the taskSymbol
     */
    @Override
    public String getTaskSymbol() {
        return super.getTaskSymbol() + "[E]";
    }

    /**
     * Returns the start date formatted as "Month Date, Year".
     *
     * @return The formatted start date as a {@code String}.
     */
    public String getStart() {
        if (this.start == null) {
            return "No start date set";
        }
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm");
        // Format the LocalDate object
        return this.start.format(formatter);
    }

    /**
     * Returns the end date formatted as "Month Date, Year".
     *
     * @return The formatted end date as a {@code String}.
     */
    public String getEnd() {
        if (this.end == null) {
            return "No deadline set";
        }
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm");
        // Format the LocalDate object
        return this.end.format(formatter);
    }
}
