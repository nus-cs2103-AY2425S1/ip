package david.Task;

import david.Parser.DateParser;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private String event;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String eventName, LocalDateTime from, LocalDateTime to, boolean completed) {
        super(eventName, completed);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toCacheString() {
        return "E|" + (this.isCompleted() ? "1" : "0") + "|"
                + this.getTask() + "|" + DateParser.formatCacheDate(this.from)
                + "|" + DateParser.formatCacheDate(this.to);
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[E]" + "[" + isCompleted + "] " + super.getTask()
                + " (from: " + DateParser.formatOutputDate(this.from)
                + " to: " + DateParser.formatOutputDate(this.to) + ")";
    }
}
