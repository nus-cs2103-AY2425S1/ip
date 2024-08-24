package myapp.task;

import myapp.core.DateTimeHandler;

import java.time.LocalDateTime;

/**
 * The Event class represents a task that has a start and end date/time.
 * It extends the {@link Task} class and includes two {@link LocalDateTime} fields
 * representing the start and end of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start date/time, and end date/time.
     *
     * @param description the description of the event.
     * @param from the {@link LocalDateTime} when the event starts.
     * @param to the {@link LocalDateTime} when the event ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return the {@link LocalDateTime} when the event starts.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return the {@link LocalDateTime} when the event ends.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event object, including its type, description, start, and end date/time.
     *
     * @return a string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeHandler.format(from) +
                " to: " + DateTimeHandler.format(to) + ")";
    }

    /**
     * Returns a string formatted for saving to a file, including the task type,
     * completion status, description, start date/time, and end date/time.
     *
     * @return a string representation of the Event object formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                DateTimeHandler.format(from, true) + " | " +
                DateTimeHandler.format(to, true);
    }
}
