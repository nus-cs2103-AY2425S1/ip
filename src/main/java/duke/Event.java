package duke;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start date and time, and end date and time.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

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
        int startDay = this.start.getDayOfMonth();
        Month startMonth = this.start.getMonth();
        int startYear = this.start.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String startTime12HourFormat = this.start.format(formatter);
        String startDate = startDay + " " + startMonth + " " + startYear + " " + startTime12HourFormat;

        int endDay = this.end.getDayOfMonth();
        Month endMonth = this.end.getMonth();
        int endYear = this.end.getYear();
        String endTime12HourFormat = this.end.format(formatter);
        String endDate = endDay + " " + endMonth + " " + endYear + " " + endTime12HourFormat;

        return this.getTypeIcon() + super.toString() + " (from: " + startDate + " " +
                "to: " + endDate + ")";
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
