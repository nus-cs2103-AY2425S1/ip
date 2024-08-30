import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDateTime deadlineDateTime;

    public DeadlineTask(String taskDescription, String deadlineString) {
        super(taskDescription);
        try {
            this.deadlineDateTime = LocalDateTime.parse(deadlineString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    private DeadlineTask(boolean isDone, String taskDescription, LocalDateTime deadlineDateTime) {
        super(isDone, taskDescription);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new DeadlineTask(true, super.taskDescription, this.deadlineDateTime);
    }

    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new DeadlineTask(false, super.taskDescription, this.deadlineDateTime)
            : this;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDateTime
            .format(prettyDateTimeFormatter) + ")";
    }
}
