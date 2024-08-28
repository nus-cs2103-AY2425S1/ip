package hue.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import hue.DateUtils;
/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * Creates an {@code Event} task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event in the format "yyyy-MM-dd HH:mm".
     * @param to The end date and time of the event in the format "yyyy-MM-dd HH:mm".
     */
    public Event (String description, String from, String to) {
        super(description);
        this.from = DateUtils.parseDateTime(from);
        this.to = DateUtils.parseDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        String task = "E";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " +
                this.from.format(DateUtils.DATE_TIME_FORMATTER)
                + " | "
                + this.to.format(DateUtils.DATE_TIME_FORMATTER);
    }
}
