package lexi.task;

import java.time.LocalDateTime;

/**
 * Represents an event task in the Lexi application.
 * An event has a start time and an end time in addition to a name and status.
 */
public class Event extends DatedTask {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified name, start time, and end time.
     *
     * @param taskName The name of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the formatted date and time as a string.
     *
     * @param range The date and time to be formatted.
     * @return The formatted date and time as a string.
     */
    public String getFormattedDateAndTime(LocalDateTime range) {
        return range.format(DatedTask.outputFormatter);
    }

    /**
     * Returns the string representation of the event.
     * The string includes the type of task (indicated by "[E]"), the task's name,
     * status, start time, and end time.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.getFormattedDateAndTime(from)
                + " to: " + this.getFormattedDateAndTime(to) + ")";
    }
}
