package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event that is a task with start and end date
 *
 * @author Ernest Lim
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor for Events object
     *
     * @param description description of event task
     * @param start start time of the event
     * @param end end time of the event
     * @throws DateTimeParseException if the format provided is not the same as INPUT_FORMATTER
     */
    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        this.startDate = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.endDate = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(OUTPUT_FORMATTER)
                + " to: " + endDate.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns Event as a fancier string e.g. Event | 1 | 23 | /from 11-11-1111 1111 /to 02-02-1122 1221
     * Meant for recording in text files
     *
     * @return Fancier string of the Event
     */
    @Override
    public String toFancyString() {
        return "Event | " + super.getStatus() + " | " + super.description
                + " | /from " + super.reverseLocalDateTimeParse(startDate)
                + " /to " + super.reverseLocalDateTimeParse(endDate);
    }
}
