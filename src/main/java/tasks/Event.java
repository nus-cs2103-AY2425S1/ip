package tasks;

import java.time.LocalDateTime;

import parser.DateTimeHandler;

/**
 * The {@code Event} class represents a task that occurs at a specific time interval.
 * It extends the {@code Task} class and includes additional information about the
 * start time and end time of the event.
 */
public class Event extends Task {

    private final LocalDateTime startTime;

    private final LocalDateTime endTime;

    /**
     * Constructs a new {@code Event} task with the specified name, start time, and end time.
     *
     * @param name      The name or description of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns a string representation of the event task, including its completion status,
     * name, start time, and end time.
     *
     * @return A string representation of the task in the format
     *         "[E] name (from: startTime to: endTime)".
     */
    @Override
    public String toString() {
        String start = DateTimeHandler.formatDateTime(this.startTime);
        String end = DateTimeHandler.formatDateTime(this.endTime);
        return ("[E] " + super.toString() + " (from: " + start + " to: " + end + ")");
    }
}

