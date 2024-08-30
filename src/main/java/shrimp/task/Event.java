package shrimp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs during a specific time period.
 * An {@code Event} task has a description, a start time, an end time, and a completion status.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, end time, and completion status.
     *
     * @param description  The description of the task.
     * @param from         The start time of the event.
     * @param to           The end time of the event.
     * @param hasCompleted The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean hasCompleted) {
        super(description, hasCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * Marks this task as done and returns a new {@code Event} instance with the updated status.
     *
     * @return A new {@code Event} instance marked as done.
     */
    @Override
    public Event markAsDone() {
        return new Event(getDescription(), this.from, this.to, true);
    }

    /**
     * Marks this task as not done and returns a new {@code Event} instance with the updated status.
     *
     * @return A new {@code Event} instance marked as not done.
     */
    @Override
    public Event markAsNotDone() {
        return new Event(getDescription(), this.from, this.to, false);
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task, "[E]" for Event.
     */
    public String getType() {
        return "[E]";
    }

    /**
     * Retrieves the start time of this event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of this event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the task, including the start and end times.
     *
     * @return A string in the format "[E][status] description (from: dd/MM/yyyy to: dd/MM/yyyy)".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getType() + super.toString() + " (from: " + from.format(pattern) + " to: " + to.format(pattern) + ")";
    }

    @Override
    public Event markAsDone() {
        return new Event(getDescription(), this.from, this.to, true);
    }

    @Override
    public Event markAsNotDone() {
        return new Event(getDescription(), this.from, this.to, false);
    }

    public String getType() {
        return "[E]";
    }
}
