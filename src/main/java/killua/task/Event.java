package killua.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the Killua application.
 * This class handles events that can be scheduled with specific start and end times or dates.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event with specified start and end date-times.
     *
     * @param description Description of the event.
     * @param fromDateTime Start date-time of the event.
     * @param toDateTime End date-time of the event.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.startDateTime = fromDateTime;
        this.endDateTime = toDateTime;
    }

    /**
     * Constructs an Event with specified start and end dates.
     *
     * @param description Description of the event.
     * @param fromDate Start date of the event.
     * @param toDate End date of the event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.startDate = fromDate;
        this.endDate = toDate;
    }

    /**
     * Formats the start and end dates or date-times of the event into an array of strings.
     * Uses "MMM d yyyy HH:mm" format for date-times and "MMM d yyyy" format for dates.
     *
     * @return An array of formatted strings representing the start and end date or date-time.
     */
    public String[] format() {
        if (startDateTime != null && endDateTime != null) {
            return new String[]{
                    startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                    endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            };
        } else if (startDate != null && endDate != null) {
            return new String[]{
                    startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            };
        }
        return new String[]{};
    }

    /**
     * Returns the start date of the event.
     * If the event uses LocalDateTime, it returns the date part of the start date-time.
     *
     * @return The start date of the event.
     */
    public LocalDate getStartDate() {
        if (startDate != null) {
            return startDate;
        }
        return startDateTime.toLocalDate();
    }

    /**
     * Returns the end date of the event.
     * If the event uses LocalDateTime, it returns the date part of the end date-time.
     *
     * @return The end date of the event.
     */
    public LocalDate getEndDate() {
        if (endDate != null) {
            return endDate;
        }
        return endDateTime.toLocalDate();
    }

    /**
     * Returns a string representation of this event in a format suitable for saving to a file.
     * The format includes the event type, completion status, description, start date, and end date.
     *
     * @return A string representation of the event for saving.
     */
    @Override
    public String toSave() {
        return "E" + super.toSave() + " | " + format()[0] + " | " + format()[1];
    }

    /**
     * Returns a string representation of this event in a user-friendly format.
     * The format includes the event type, completion status, description, start date, and end date.
     *
     * @return A string representation of the event for display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + format()[0] + " to: " + format()[1] + ")";
    }

    /**
     * Returns the start date-time of the event.
     *
     * @return The start date-time of the event, or null if the event uses LocalDate.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end date-time of the event.
     *
     * @return The end date-time of the event, or null if the event uses LocalDate.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
