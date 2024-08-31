package sigma.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time period.
 * Inherits from the {@link Task} class and adds a start time and an end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * Converts this {@code Event} task to a string representation suitable for saving to a file.
     *
     * @return the string representation of this task in the format "E | status | description | from | to"
     */
    @Override
    public String stringify() {
        return String.format("E | %d | %s | %s | %s",
                this.isDone ? 1 : 0, this.description, this.from.format(FORMATTER), this.to.format(FORMATTER));
    }

    /**
     * Returns the string representation of this {@code Event} task.
     *
     * @return the string representation of this task as "[E][status] description (from: start time to: end time)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(FORMATTER)
                + " to: " + this.to.format(FORMATTER) + ")";
    }
}
