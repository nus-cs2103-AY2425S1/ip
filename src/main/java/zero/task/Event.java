package zero.task;

import java.time.LocalDateTime;

/**
 * The {@code Event} class represents a task that occurs within a specific time frame.
 * It is a subclass of {@code Task} and adds two {@code LocalDateTime} fields to store the start (from)
 * and end (to) times.
 */
public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toYear;

    public Event(String description, LocalDateTime fromDate, LocalDateTime toYear) {
        super(description);
        this.fromDate = fromDate;
        this.toYear = toYear;
    }

    @Override
    public String toFormatted() {
        return "E," + this.isDone() + "," + this.description + "," + this.fromDate + "," + this.toYear + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.getDayOfMonth() + " " + fromDate.getMonth() + " "
                + fromDate.getYear() + " to: " + toYear.getDayOfMonth() + " " + toYear.getMonth() + " " + toYear.getYear() + ")";
    }
}
