import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
     * Parses the input date string. If it contains only a date without time,
     * appends "T00:00" to default the time to midnight.
     */
    private LocalDateTime parseDateTimeOrDate(String dateStr) {
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
        return this.getTaskSymbol() + " " + super.toString() + " (from: " + start + " to: " + end + ")";
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
     * Returns the start date of the Event Task
     *
     * @return The start date of the Event Task
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Returns the end date of the Event Task
     *
     * @return The end date of the Event Task
     */
    public String getEnd() {
        return this.end;
    }
}
