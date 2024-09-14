import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /**
     * Constructs a Deadline task with the specified description and deadline
     *
     * @param description The description of the task provided by the user
     * @param deadline The deadline of the task provided by the user
     */
    private LocalDateTime deadline;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDateTimeOrDate(deadline);
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
     * Returns a string representation of the Deadline object.
     * The string includes a "[D]" prefix to indicate that this is a Deadline task,
     * followed by the string representation provided by the Task superclass.
     *
     * @return A string in the format: "[D] <super.toString()>"
     */
    @Override
    public String toString() {
        return this.getTaskSymbol() + " " + super.toString() + " (by: " + this.deadline + ")";
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
     * Returns the deadline of the Deadline Task
     *
     * @return The deadline of the Deadline Task
     */
    public String getDeadline() {
        return this.deadline;
    }
}
