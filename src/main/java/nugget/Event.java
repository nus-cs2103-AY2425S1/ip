package nugget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an <code>Event</code> task with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param start The start date/time of the event in the format "yyyy-MM-dd HHmm".
     * @param end The end date/time of the event in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the start or end date/time is not in the correct format.
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Returns the task type of the event.
     *
     * @return A string "[E]" indicating the event task type.
     */
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event, including its description,
     * start date/time, and end date/time.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        String startFormatted = this.start.format(formatter);
        String endFormatted = this.end.format(formatter);
        return String.format("%s%s (from: %s to: %s)", this.getTaskType(),
                super.toString(), startFormatted, endFormatted);
    }
}
