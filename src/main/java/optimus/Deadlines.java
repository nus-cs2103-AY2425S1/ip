package optimus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//AI helped with writing a more precise documentation
/**
 * Represents a deadline task with a specific due date and time.
 * This class extends the {@code Task} class and includes a due date/time for the task.
 * The due date is represented both as a string and as a {@code LocalDateTime} object.
 */
public class Deadlines extends Task {

    private String by;  // The string representation of the due date and time.
    private LocalDateTime dateTime;  // The parsed LocalDateTime object representing the due date and time.

    /**
     * Constructs a {@code Deadlines} object with the specified task description and due date/time.
     *
     * @param description The task description.
     * @param by The string representation of the due date/time for the task.
     * @throws OptimusException if the date-time string is invalid or cannot be parsed.
     */
    public Deadlines(String description, String by) throws OptimusException {
        super(description);
        this.by = by;
        this.dateTime = parseStringDeadline(by);  // Parse the string into a LocalDateTime object.
    }

    /**
     * Parses the provided date-time string into a {@code LocalDateTime} object.
     * The method checks if the input string matches one of the supported date-time formats.
     * If none of the formats match, an {@code OptimusException} is thrown.
     *
     * @param dateTime The string representation of the date and time.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws OptimusException if the input string cannot be parsed into a valid date/time format.
     */
    LocalDateTime parseStringDeadline(String dateTime) throws OptimusException {
        if (dateTime == null || dateTime.trim().isEmpty()) {
            throw new OptimusException("Date-time string cannot be null or empty.");
        }
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm")
                // Different acceptable formats to be parsed
        };

        for (DateTimeFormatter diffFormat : formats) {
            try {
                return LocalDateTime.parse(dateTime, diffFormat);
            } catch (DateTimeParseException e) {
                // Continue to the next format if parsing fails
            }
        }
        throw new OptimusException("Invalid date-time format. Please use one of the following formats: " +
                "d/MM/yyyy HH:mm, yyyy-MM-dd HH:mm, d-MM-yyyy HH:mm, yyyy/MM/d HH:mm.");
    }

    /**
     * Returns a string representation of the {@code Deadlines} task, including its status,
     * description, and due date/time. The date/time is formatted to show as "MMM d yyyy, h:mma".
     *
     * @return A string that represents the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Returns the string representation of the {@code Deadlines} task formatted for saving to a file.
     * The string is formatted in the form "D | doneStatus | description | by".
     *
     * @return A string formatted for saving the deadline task to a file.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
