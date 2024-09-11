package monobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    private static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description
     *
     * @param desc The description of the Event task.
     * @param from Start date/time of event.
     * @param to End date/time of event.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDateTime.parse(from.trim(), PARSER);
        this.to = LocalDateTime.parse(to.trim(), PARSER);
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "[E][X] " : "[E][ ] ";
        return status + this.description.trim() + " (from: " + from.format(FORMATTER) + " to: "
                + to.format(FORMATTER) + ")";
    }
}
