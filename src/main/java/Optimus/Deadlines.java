package Optimus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specific due date and time.
 * A {@code Deadlines} object stores the task description, due date, and time.
 */
public class Deadlines extends Task {

    private String by;  // The string representation of the due date and time.
    private LocalDateTime dateTime;  // The parsed LocalDateTime object representing the due date and time.

    /**
     * Constructs a {@code Deadlines} object with the specified task description and due date/time.
     *
     * @param description the description of the deadline task.
     * @param by the string representation of the due date and time in specific formats.
     * @throws OptimusException if the date-time string cannot be parsed or is null/empty.
     */
    public Deadlines(String description, String by) throws OptimusException {
        super(description);
        this.by = by;
        this.dateTime = parseStringDeadline(by);
    }

    /**
     * Parses the provided date-time string into a {@code LocalDateTime} object.
     * The method checks if the input string matches one of the supported date-time formats.
     * <p>
     * Supported formats:
     * <ul>
     * <li>d/MM/yyyy HH:mm</li>
     * <li>yyyy-MM-dd HH:mm</li>
     * <li>d-MM-yyyy HH:mm</li>
     * <li>yyyy/MM/d HH:mm</li>
     * </ul>
     *
     * @param dateTime the string representation of the due date and time.
     * @return the {@code LocalDateTime} object.
     * @throws OptimusException if the date-time string is null or empty, or does not match any of the supported formats required.
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
     * Returns the string representation of the deadline task, including its status, description, and due date/time.
     *
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Returns the string representation of the deadline task formatted for saving to a file.
     *
     * @return the formatted string for saving the deadline task.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
