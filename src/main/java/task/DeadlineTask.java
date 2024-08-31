package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import utility.CustomDateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadlineDateTime;

    public DeadlineTask(String taskDescription, LocalDateTime deadlineDateTime) {
        super(taskDescription);
        this.deadlineDateTime = deadlineDateTime;
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
            .format(CustomDateTimeFormatter.PRETTY_DATE_TIME_FORMATTER) + ")";
    }
}
