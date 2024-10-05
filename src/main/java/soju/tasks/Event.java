package soju.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task of type "Event".
 * It extends the {@code Task} class and includes additional functionality
 * for managing event-specific details such as start and end times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the end time of the event formatted as "MMM dd yyyy hh:mm a".
     *
     * @return A formatted string representing the end time of the event.
     */
    public String getToDate() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns a string representation of the start time of the event formatted as "MMM dd yyyy hh:mm a".
     *
     * @return A formatted string representing the start time of the event.
     */
    public String getFromDate() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Detects if there is a clash between this event and another event.
     * A clash occurs when the time intervals of the two events overlap.
     *
     * @param otherEvent The other event to check for a time clash.
     * @return The other event if there is a clash; {@code null} otherwise.
     */
    public boolean isClashing(Event otherEvent) {
        // Check if the events overlap in time
        if (this.to.isAfter(otherEvent.from) && this.from.isBefore(otherEvent.to)) {
            // There is a clash
            return true;
        }
        // No clash
        return false;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFromDate(), getToDate());
    }

    /**
     * Returns a string representation of the "Event" task formatted for saving to a file.
     * The format is "E | status | description | from - to", where status is 1 if the task is done
     * and 0 if the task is not done. The from and to times are represented as ISO-8601 strings.
     *
     * @return A string representation of the "Event" task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s - %s", isDone ? 1 : 0, description, from, to);
    }
}
