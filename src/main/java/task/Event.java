package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which contains a description, a start time, and an end time.
 * An Event task is characterized by the period it spans from a specific start time to a specific end time.
 * It can also be marked as done or not done.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event object with a description, start time, end time, and sets its completion status to not done
     * by default.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs an Event object with a description, start time, end time, and allows setting its completion status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        this.to = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the type of Event.
     *
     * @return Type of event.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Snoozes the event by the specified number of days.
     *
     * This method adjusts the event's start and end date by adding the specified number of days
     * to both the start and end dates.
     *
     * @param days The number of days to snooze the event by.
     */
    public void snooze(int days) {
        this.from = this.from.plusDays(days);
        this.to = this.to.plusDays(days);
    }

    /**
     * Returns a string representation of the event,its type, status,
     * description, start time, and end time.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: "
                + to.format(OUTPUT_FORMATTER) + ")";
    }
}
