package lutchat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific period.
 * An Event is a task that has a start date and an end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param from        The start date of the event, in the format "yyyy-MM-dd".
     * @param to          The end date of the event, in the format "yyyy-MM-dd".
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    /**
     * Returns the task type.
     *
     * @return A string representing the task type, which is "E" for Event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns additional details for file formatting.
     *
     * @return A string containing the start and end dates in the format "yyyy-MM-dd".
     */
    @Override
    public String additionalDescDetailsToFileFormat() {
        return " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string representing the Event, including its description and the date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
