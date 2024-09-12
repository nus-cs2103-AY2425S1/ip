package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs during a specific time period.
 * An {@code Event} task has a description, a start time, an end time, and a completion status.
 */
public class Event extends Task {
    private final LocalDateTime eventStart;
    private final LocalDateTime eventEnd;

    /**
     * Constructs an {@code Event} task with the specified description, start time, end time, and completion status.
     *
     * @param description  The description of the task.
     * @param eventStart   The start time of the event.
     * @param eventEnd     The end time of the event.
     * @param hasCompleted The completion status of the event.
     */
    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd, Boolean hasCompleted) {
        super(description, hasCompleted);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Marks this task as done and returns a new {@code Event} instance with the updated status.
     *
     * @return A new {@code Event} instance marked as done.
     */
    @Override
    public Event markAsDone() {
        return new Event(getDescription(), this.eventStart, this.eventEnd, true);
    }

    /**
     * Marks this task as not done and returns a new {@code Event} instance with the updated status.
     *
     * @return A new {@code Event} instance marked as not done.
     */
    @Override
    public Event markAsNotDone() {
        return new Event(getDescription(), this.eventStart, this.eventEnd, false);
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, "[E]" for Event.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Retrieves the start time of this event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getEventStart() {
        return eventStart;
    }

    /**
     * Retrieves the end time of this event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    /**
     * Returns a string representation of the task, including the start and end times.
     *
     * @return A string in the format "[E][status] description (from: dd/MM/yyyy to: dd/MM/yyyy)".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getType() + super.toString() + " (from: " + eventStart.format(pattern) + " to: "
                + eventEnd.format(pattern) + ")";
    }
}
