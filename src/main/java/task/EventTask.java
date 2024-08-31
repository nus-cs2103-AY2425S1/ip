package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import utility.CustomDateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public EventTask(String taskDescription, LocalDateTime fromDateTime,
            LocalDateTime toDateTime) {
        super(taskDescription);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    private EventTask(boolean isDone, String taskDescription,
            LocalDateTime from, LocalDateTime to) {
        super(isDone, taskDescription);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    @Override
    public Task markAsDone() {
        return super.isDone
            ? this 
            : new EventTask(true, super.taskDescription, this.fromDateTime, this.toDateTime);
    }

    @Override 
    public Task markAsUndone() {
        return super.isDone
            ? new EventTask(false, super.taskDescription, this.fromDateTime, this.toDateTime)
            : this;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " 
            + fromDateTime.format(CustomDateTimeFormatter.PRETTY_DATE_TIME_FORMATTER) + " to: " 
            + toDateTime.format(CustomDateTimeFormatter.PRETTY_DATE_TIME_FORMATTER) + ")";
    }
}
