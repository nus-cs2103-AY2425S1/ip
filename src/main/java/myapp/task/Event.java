package myapp.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task that occurs during a specific time period.
 * It extends the {@code Task} class and includes additional information about
 * the start and end times of the event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new {@code Event} task with the specified description,
     * start time, and end time.
     *
     * @param description A {@code String} describing the event.
     * @param from        A {@code LocalDateTime} object representing the start time of the event.
     * @param to          A {@code LocalDateTime} object representing the end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return A {@code LocalDateTime} object representing the start time.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return A {@code LocalDateTime} object representing the end time.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns a {@code String} representation of the event task.
     * The string includes the type of task, description, and formatted start and end times.
     * If either date is invalid, it shows "Invalid Date".
     *
     * @return A {@code String} representation of this event task.
     */
    @Override
    public String toString() {
        if (from == null || to == null) {
            return "[E]" + super.toString() + " (from: Invalid Date to: Invalid Date)";
        }
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}
