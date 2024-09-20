package johncena.tasks;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * An tasks.Event object corresponds to a task represented by a description, a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for tasks.Event class.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time of the event.
     */
    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time of the event.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Checks if the task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the task occurs on the given date, false otherwise.
     */
    @Override
    public boolean occursOn(LocalDate date) {
        return this.from.toLocalDate().equals(date) || this.to.toLocalDate().equals(date);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
