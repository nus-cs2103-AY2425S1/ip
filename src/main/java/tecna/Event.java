package tecna;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents the Event type of Task, which has the starting ending times.
 * A <code>from</code> and <code>to</code> attributes stores and starting time and ending time, respectively.
 *
 * @author DennieDan.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
        if (isDone) {
            super.markAsDone();
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + this.from.format(pattern) + " to: " + this.to.format(pattern) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event event = (Event) obj;
        return taskName.equals(event.taskName) && from.equals(event.from) && to.equals(event.to);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode() + from.hashCode() + to.hashCode();
    }
}
