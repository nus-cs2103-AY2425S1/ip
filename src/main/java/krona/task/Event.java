package krona.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event with a timeline from a start date
 * and time to an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Contructs a new Event task.
     *
     * @param description The desription of the event.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime The end date and time of the event.
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        try {
            this.startDateTime = LocalDateTime.parse(startDateTime.trim(), formatter);
            this.endDateTime = LocalDateTime.parse(endDateTime.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'd/M/yyyy HHmm'.");
            throw e;  // Re-throw the exception after logging it
        }
    }

    public String getStartDateTime() {
        return startDateTime.format(formatter);
    }

    public String getEndDateTime() {
        return endDateTime.format(formatter);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) +
                " to: " + endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}