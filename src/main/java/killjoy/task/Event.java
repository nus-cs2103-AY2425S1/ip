package killjoy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskInfo() {
        return "EVENT|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|" +
                String.valueOf(this.from).replace("T", " ") + "|"
                + String.valueOf(this.to).replace("T", " ") + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (from: " + this.from.format(FORMATTER) + " to: "
                + this.to.format(FORMATTER) + ")";
    }
}
