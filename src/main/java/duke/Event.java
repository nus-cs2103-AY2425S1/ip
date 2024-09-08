package duke;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date and time, and end date and time.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTi

    /**
     * Constructs an {@code Event} task with the specified description,
     * start date and time, and end date and time.
     *
     * @param description the description of the task
     * @param start       the start date and time of the event
     * @param end         the end date and time of the event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type icon representing an event task.
     *
     * @return the type icon for an event task
     */
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a string representation of the event task,
     * including its type, status, description, start date and time,
     * and end date and time.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        String startDate = getDate(this.start);
        String endDate = getDate(this.end);
        return this.getTypeIcon() + super.toString() + " (from: " + startDate + " " +
                "to: " + endDate + ")";
    }

    private String get12HourFormat(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return dateTime.format(formatter);
    }

    private String getDate(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        Month month = dateTime.getMonth();
        int year = dateTime.getYear();;
        String time12HourFormat = get12HourFormat(dateTime);
        String date = day + " " + month + " " + year + " " + time12HourFormat;
        return date;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return the start date and time of the event
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return the end date and time of the event
     */
    public LocalDateTime getEnd() {
        return this.end;
    }
}
