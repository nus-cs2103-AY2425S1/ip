package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}