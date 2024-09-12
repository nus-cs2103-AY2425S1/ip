package shoai;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a time range (event). This class extends the Task class to include
 * a start and end datetime for the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start datetime, and end datetime.
     *
     * @param description The description of the task.
     * @param from The start datetime of the event.
     * @param to The end datetime of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return The start datetime of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return The end datetime of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
