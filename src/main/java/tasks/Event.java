package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event represents a specific subset of Task that comes with an additional duration
 */
public class Event extends Task {

    protected LocalDate fromDuration;
    protected LocalDate toDuration;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * The Event class constructor that takes in taskDescription, fromDuration and toDuration
     * @param description the description of the task
     * @param fromDuration the starting date of the event
     * @param toDuration the ending date of the event
     * @param priority the priority of the task
     */
    public Event(String description, LocalDate fromDuration, LocalDate toDuration, int priority) {
        super(description, priority);
        this.toDuration = toDuration;
        this.fromDuration = fromDuration;
        Task.incrementTaskCount();
    }
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

    /**
     * Returns the starting date of the event
     * @return the starting date of the event
     */
    public LocalDate getFromDur() {
        return this.fromDuration;
    }

    /**
     * Returns the ending date of the event
     * @return the ending date of the event
     */
    public LocalDate getToDur() {
        return this.toDuration;
    }
}
