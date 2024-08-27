package tasks;

import java.time.LocalDateTime;
import parser.DateTimeHandler;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }
    @Override
    public String toString() {
        String start = DateTimeHandler.formatDateTime(this.startTime);
        String end = DateTimeHandler.formatDateTime(this.endTime);
        if (super.isComplete()) {
            return ("[E] " + super.toString() + " (from: " + start + " to: " + end + ")");
        }
        return ("[E] " + super.toString() + " (from: " + start + " to: " + end + ")");
    }
}

