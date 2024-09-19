package task;

import java.time.LocalDateTime;

import utility.CustomDateTimeFormatter;
import utility.Tag;

/**
 * The deadline task is a specific implementation of {@link Task} with one additional date
 * time attribute to denote deadline for the task.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadlineDateTime;

    /**
     * {@inheritDoc}
     */
    public DeadlineTask(String taskDescription, LocalDateTime deadlineDateTime, Tag taskTag) {
        super(taskDescription, taskTag);
        this.deadlineDateTime = deadlineDateTime;
    }

    private DeadlineTask(boolean isDone, String taskDescription,
            LocalDateTime deadlineDateTime, Tag taskTag) {
        super(isDone, taskDescription, taskTag);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new DeadlineTask(true, super.taskDescription,
                    this.deadlineDateTime, this.taskTag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsUndone() {
        return super.isDone
            ? new DeadlineTask(false, super.taskDescription,
                    this.deadlineDateTime, this.taskTag)
            : this;
    }

    /**
     * {@inheritDoc}
     * Additionally, {@code "[D]"} is prepended to the string representation to
     * indicate this is a task with a deadline. The date and time of the deadline is
     * appended to the string representation also.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDateTime
            .format(CustomDateTimeFormatter.DATE_TIME_FORMATTER_PRETTY) + ")";
    }
}
