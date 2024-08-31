package lbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String taskType = "[E]";

    /**
     * Constructs a {@link Event} object.
     * Sets event to be incomplete.
     *
     * @param description   of the event.
     * @param startDateTime of the event.
     * @param endDateTime   of the event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = taskType;
    }

    /**
     * Constructs a {@link Event} object.
     * Allows status of event to be declared.
     *
     * @param description   of the event.
     * @param isComplete    status of the deadline.
     * @param startDateTime of the event.
     * @param endDateTime   of the event.
     */
    public Event(String description, boolean isComplete, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isComplete);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = taskType;
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description
                + " (from: " + this.startDateTime.format(dateTimeFormat)
                + " to: " + this.endDateTime.format(dateTimeFormat) + ")";
    }
}
