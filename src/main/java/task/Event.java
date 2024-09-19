package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task that occurs during a specific period of time.
 * It extends the {@code Task} class and includes {@code LocalDateTime} objects to store
 * the start and end times of the event.
 */
public class Event extends Task {
    private final LocalDateTime FROM;
    private final LocalDateTime TO;

    /**
     * Constructs a new {@code Event} task with a description, start time, and end time.
     *
     * @param desc The description of the event.
     * @param from The start date and time of the event, as a {@code LocalDateTime} object.
     * @param to   The end date and time of the event, as a {@code LocalDateTime} object.
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Returns the type of the task as a string. For an event, this is always "[E]".
     *
     * @return A string representing the type of task, which is "[E]" for events.
     */
    @Override
    public String getType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the {@code Event} task,
     * including the description, formatted start time, and end time.
     * The times are displayed in the format "MMM dd yyyy, h:mm a".
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return super.toString() + " (from: " + this.FROM.format(formatter) + " to: " + this.TO.format(formatter) + ")";
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return A {@code LocalDateTime} object representing the start time.
     */
    public LocalDateTime getFrom() {
        return this.FROM;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return A {@code LocalDateTime} object representing the end time.
     */
    public LocalDateTime getTo() {
        return this.TO;
    }
}
