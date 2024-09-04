package lebron;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a start and end date. An Event task
 * includes a description, start date, and end date.
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event task with the specified description, start date, and
     * end date.
     *
     * @param description The description of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task. The string includes
     * the task type, status icon, description, and formatted start and end
     * dates.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcons(),
                super.description, this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the event task formatted for file
     * storage. The string includes the task type, completion status,
     * description, and the raw start and end dates.
     *
     * @return A string representing the event task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s to %s", this.done ? 1 : 0, this.description,
                this.start, this.end);
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDate getEnd() {
        return this.end;
    }
}
