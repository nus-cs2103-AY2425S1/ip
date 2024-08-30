package justbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Justbot application.
 * An Event is a task that occurs during a specific time period, with a start time and an end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date and time as a {@link LocalDateTime} object.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end date and time as a {@link LocalDateTime} object.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Returns a string representation of the event task.
     * The string includes the task type "[E]", the task description, and the formatted start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, hhmma");

        String formattedStart = this.start.format(formatter).replace("AM", "hrs").replace("PM", "hrs");
        String formattedEnd = this.end.format(formatter).replace("AM", "hrs").replace("PM", "hrs");

        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
