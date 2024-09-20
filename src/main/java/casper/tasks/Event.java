package casper.tasks;

import static casper.utils.DateTimeUtils.convertDateTimeToString;

import java.time.LocalDateTime;

/**
 * Represents an event task with a description, start date-time, and end date-time.
 * Inherits from {@link Task} and includes functionality for setting and displaying the event details.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} with the specified description, start date-time, and end date-time.
     *
     * @param description A brief description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new {@code Event} with the specified description, start and end date-time, and completion status.
     *
     * @param description A brief description of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date and time of the task.
     *
     * @return A {@link LocalDateTime} object representing the start date and time of the task.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns a string representation of the event task, including the description, start date-time, and end date-time.
     *
     * @return A string in the format: "[E][completed] description (from: start date-time to: end date-time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + convertDateTimeToString(this.from)
                + " to: " + convertDateTimeToString(this.to) + ")";
    }

    /**
     * Returns a string representation of the event task suitable for saving to a file.
     *
     * @return A string in the format: "event | done status | description | start date-time | end date-time".
     */
    @Override
    public String toSaveString() {
        return String.format("event | %d | %s | %s | %s",
                super.isDone ? 1 : 0,
                this.description,
                convertDateTimeToString(this.from),
                convertDateTimeToString(this.to));
    }

}
