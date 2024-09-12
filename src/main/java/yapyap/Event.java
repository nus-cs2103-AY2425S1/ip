package yapyap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a description, start time, and end time.
 * The Event task can be marked as done or not done.
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an Event with the specified description, start time, and end time.
     * The date-time format should be in the pattern "d/M/yyyy HHmm".
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "d/M/yyyy HHmm".
     * @param to End time of the event in the format "d/M/yyyy HHmm".
     * @throws YapperBotException If the date-time format is invalid.
     */
    public Event(String description, String from, String to) throws YapperBotException {
        super(description);
        try {
            this.startTime = parseDateTime(from);
            this.endTime = parseDateTime(to);
        } catch (DateTimeParseException e) {
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Creates an event with the specified description, start time, end time and completion status.
     * The date-time format should be in the pattern "d/M/yyyy HHmm".
     *
     * @param description Description of the event.
     * @param from Start time of the event in the format "d/M/yyyy HHmm".
     * @param to End time of the event in the format "d/M/yyyy HHmm".
     * @param isDone Completion status of the event.
     * @throws YapperBotException If the date-time format is invalid.
     */
    public Event(String description, String from, String to, boolean isDone) throws YapperBotException {
        super(description);
        this.isDone = isDone;
        try {
            this.startTime = parseDateTime(from);
            this.endTime = parseDateTime(to);
        } catch (DateTimeParseException e) {
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }
    }

    /**
     * Returns a string representation of the event, including its status, description,
     * start time, and end time formatted as "MMM dd yyyy h:mm a".
     *
     * @return Formatted string of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.startTime)
                + " to: " + formatDateTime(this.endTime) + ")";
    }

    /**
     * Converts the event to a format suitable for saving to a file.
     * The format includes the event type, status, description, start time, and end time.
     *
     * @return Formatted string representation of the event for saving.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | " + formatForSave(this.startTime)
                + " | " + formatForSave(this.endTime);
    }

    /**
     * Parses a date-time string into a LocalDateTime object using the "d/M/yyyy HHmm" pattern.
     *
     * @param dateTime The date-time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException if the date-time format is invalid.
     */
    private static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Formats a LocalDateTime object into a string using the "MMM dd yyyy h:mm a" pattern.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date-time string.
     */
    private static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }

    /**
     * Formats a LocalDateTime object into a string using the "d/M/yyyy HHmm" pattern for saving.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date-time string for saving.
     */
    private static String formatForSave(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
