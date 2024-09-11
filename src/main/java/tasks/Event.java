package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that extends Task class with a range date specifier from and to
 */
public class Event extends Task {

    protected LocalDate fromDuration;
    protected LocalDate toDuration;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * The Event class constructor that takes in taskDescription, fromDuration and toDuration
     * @param description
     * @param fromDuration
     * @param toDuration
     */
    public Event(String description, LocalDate fromDuration, LocalDate toDuration, int priority) {
        super(description, priority);
        this.toDuration = toDuration;
        this.fromDuration = fromDuration;
        Task.incrementTaskCount();
    }

    /*@Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + fromDuration + " | " + toDuration;
    }*/
    @Override
    public String toDataString() {
        return "E | " + super.toDataString() + " | " + fromDuration + " | " + toDuration + " | "
                + super.getPriorityNum();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDuration.format(outputFormatter)
                + " to: " + this.toDuration.format(outputFormatter) + ")";
    }

    public LocalDate getFromDur() {
        return this.fromDuration;
    }

    public LocalDate getToDur() {
        return this.toDuration;
    }
}
