package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import utility.CustomDateTimeFormatter;

/**
 * The deadline task is a specific implementation of {@link Task} with one additional date
 * time attribute to denote deadline for the task.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadlineDateTime;

    /**
     * {@inherit}
     */
    public DeadlineTask(String taskDescription, LocalDateTime deadlineDateTime) {
        super(taskDescription);
        this.deadlineDateTime = deadlineDateTime;
    }

    private DeadlineTask(boolean isDone, String taskDescription, LocalDateTime deadlineDateTime) {
        super(isDone, taskDescription);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * {@inherit}
     */
    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new DeadlineTask(true, super.taskDescription, this.deadlineDateTime);
    }

    /**
     * {@inherit}
     */
    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new DeadlineTask(false, super.taskDescription, this.deadlineDateTime)
            : this;
    }

    /**
     * {@inherit}
     * Additionally, {@code "[D]"} is prepended to the string representation to
     * indicate this is a task with a deadline. The date and time of the deadline is
     * appended to the string representation also.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDateTime
            .format(CustomDateTimeFormatter.PRETTY_DATE_TIME_FORMATTER) + ")";
    }
}
