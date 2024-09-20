package voidcat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which has a start and end time.
 * The start and end, known as from and to, each consists of a date and time.
 */
public class Event extends Task {
    protected static final DateTimeFormatter FORMATTER_DATETIME = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start and end time,
     * and completion status to save to a file.
     *
     * @param description The description of the event.
     * @param from The start date and time of the event in the format "uuuu-MM-dd HHmm".
     * @param to The end date and time of the event in the format "uuuu-MM-dd HHmm".
     * @param done 1 if the task is done, 0 if not done.
     */
    public Event(String description, String from, String to, int done) {
        super(description);
        this.from = LocalDateTime.parse(from, FORMATTER_DATETIME);
        this.to = LocalDateTime.parse(to, FORMATTER_DATETIME);
        if (done == 1) {
            this.isDone = true;
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %d | %s | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0),
                this.description, this.from.format(FORMATTER_DATETIME), this.to.format(FORMATTER_DATETIME));
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d uuuu, h:mma")) + ")";
    }
}
