package optimus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end date/time.
 * An {@code Events} object stores the task description, start time, and end time.
 */
public class Events extends Task {

    private String from;  // The string representation of the event start time.
    private String to;  // The string representation of the event end time.
    private LocalDateTime fromStr;  // The parsed LocalDateTime object representing the start time.
    private LocalDateTime toStr;  // The parsed LocalDateTime object representing the end time.

    /**
     * Constructs an {@code Events} object with the specified task description, start time, and end time.
     *
     * @param description the description of the event task.
     * @param from the string representation of the event start time in specific formats.
     * @param to the string representation of the event end time in specific formats.
     * @throws OptimusException if the date-time strings cannot be parsed or are null/empty.
     */
    public Events(String description, String from, String to) throws OptimusException {
        super(description);
        this.from = from;
        this.to = to;
        this.fromStr = parseStringEvent(from);
        this.toStr = parseStringEvent(to);
    }

    /**
     * Parses the provided date-time string into a {@code LocalDateTime} object.
     * The method checks if the input string matches one of the supported date-time formats.
     *
     * Supported formats:
     * <ul>
     * <li>d/MM/yyyy HH:mm</li>
     * <li>yyyy-MM-dd HH:mm</li>
     * <li>d-MM-yyyy HH:mm</li>
     * <li>yyyy/MM/d HH:mm</li>
     * </ul>
     *
     * @param dateTime the string representation of the event start or end time.
     * @return the parsed {@code LocalDateTime} object.
     * @throws OptimusException if the date-time string is null, empty, or does not match any of the supported formats.
     */
    public LocalDateTime parseStringEvent(String dateTime) throws OptimusException {
        if (dateTime == null || dateTime.trim().isEmpty()) {
            throw new OptimusException("Date-time string cannot be null or empty.");
        }

        dateTime = dateTime.trim();

        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm"),
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
     * Returns the string representation of the event task, including its status, description, and start/end times.
     *
     * @return the string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + fromStr.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " - " + toStr.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Returns the string representation of the event task formatted for saving to a file.
     *
     * @return the formatted string for saving the event task.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
