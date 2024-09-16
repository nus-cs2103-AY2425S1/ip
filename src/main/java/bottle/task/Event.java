package bottle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Event.
 */
public class Event extends bottle.task.Task {
    /**
     * The From.
     */
    protected LocalDateTime from;
    /**
     * The To.
     */
    protected LocalDateTime to;

    /**
     * Instantiates a new Event.
     *
     * @param desc the desc
     * @param from the from
     * @param to   the to
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "E | " + (isChecked ? "1 | " : "0 | ")
                + this.taskDesc + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }
}
