package friendlybot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an friendlybot.task.Event task.
 * An friendlybot.task.Event task is a task with a description and a start and end timing.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a new friendlybot.task.Event task with the specified description and timings.
     *
     * @param description The description of the friendlybot.task.Event task.
     * @param from The start time of the friendlybot.task.Event task.
     * @param to The end time of the friendlybot.task.Event task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the friendlybot.task.Event task.
     *
     * @return A string in the format "[E][statusIcon] description (from: time to: time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
