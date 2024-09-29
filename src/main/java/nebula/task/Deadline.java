package nebula.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime deadline;
    private static final DateTimeFormatter DATE_TIME_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Deadline task with the specified description and deadline
     *
     * @param description The description of the task provided by the user
     * @param deadline The deadline of the task provided by the user
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDateTimeOrDate(deadline);
    }

    /**
     * Parses the input date string and returns a LocalDateTime object. If it
     * contains only a date without time, appends "00:00" to default the
     * time to midnight.
     *
     * @param dateStr The deadline of the task provided by the user
     * @return A LocalDateTime object to be assigned to the deadline field
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
     * Returns a string representation of the Deadline object.
     * The string includes a "[D]" prefix to indicate that this is a Deadline task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[D] <super.toString()>"
     */
    @Override
    public String toString() {
        return this.getTaskSymbol()
                + " " + super.toString() + " (by: " + getDeadline() + ")";
    }

    /**
     * Returns the taskSymbol for the Deadline object
     *
     * @return A string with the taskSymbol
     */
    @Override
    public String getTaskSymbol() {
        return super.getTaskSymbol() + "[D]";
    }

    /**
     * Returns the deadline formatted as "Month Date, Year".
     *
     * @return The formatted deadline as a {@code String}.
     */
    public String getDeadline() {
        if (this.deadline == null) {
            return "No deadline set";
        }
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm");
        // Format the LocalDate object
        return this.deadline.format(formatter);
    }
}
