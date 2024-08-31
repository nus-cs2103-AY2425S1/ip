package lbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String taskType = "[E]";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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
        return this.type + "[" + this.status() + "] " + this.description + " (from: " + this.startDateTime.format(dateTimeFormat) + " to: " + this.endDateTime.format(dateTimeFormat) + ")";
    }
}
