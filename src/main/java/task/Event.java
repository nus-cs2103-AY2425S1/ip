package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String taskType = "[E]";

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = taskType;
    }

    public Event(String description, boolean isComplete, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isComplete);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = taskType;
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description + "(from: " + this.startDateTime + " to: " + this.endDateTime + ")";
    }
}
