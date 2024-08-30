package lexi.task;

import java.time.LocalDateTime;

public class Event extends DatedTask {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }
    public LocalDateTime getFrom() {
        return this.from;
    }
    public LocalDateTime getTo() {
        return this.to;
    }
    public String getFormattedDateAndTime(LocalDateTime range) {
        return range.format((DatedTask.outputFormatter));
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.getFormattedDateAndTime(from)
                + " to: " + this.getFormattedDateAndTime(to) + ")";
    }
}
