import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    public EventTask(String taskDescription, String from, String to) {
        super(taskDescription);
        try {
            this.fromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
            this.toDateTime = LocalDateTime.parse(to, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
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
            + fromDateTime.format(prettyDateTimeFormatter) + " to: " 
            + toDateTime.format(prettyDateTimeFormatter)+ ")";
    }
}
