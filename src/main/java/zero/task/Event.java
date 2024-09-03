package zero.task;

import java.time.LocalDateTime;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It is a subclass of {@code Task} and adds two {@code LocalDateTime} fields to store the start (from)
 * and end (to) times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFormatted() {
        return "E," + this.isDone() + "," + this.description + "," + this.from + "," + this.to + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getDayOfMonth() + " " + from.getMonth() + " "
                + from.getYear() + " to: " + to.getDayOfMonth() + " " + to.getMonth() + " " + to.getYear() + ")";
    }
}
