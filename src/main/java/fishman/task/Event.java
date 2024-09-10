package fishman.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task.
 * This class extends the Task class, adding the from and to attributes to it.
 */
public class Event extends Task {
    /** The start time of the event. */
    protected LocalDateTime from;
    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        assert description != null : "Description should not be null";
        assert from != null : "Event 'From' date should not be null";
        assert to != null : "Event 'To' date should not be null";
        assert from.isBefore(to) : "Event 'From' date should be before 'To' date";
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
