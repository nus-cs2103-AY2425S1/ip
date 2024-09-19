package task;

import java.time.LocalDateTime;

import utility.CustomDateTimeFormatter;
import utility.Tag;

/**
 * The event task is a more specific implementaiton of {@link Task} with two
 * additional date
 * time attributes to denote the start and end of the event.
 */
public class EventTask extends Task {
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    /**
     * {@inheritDoc}
     */
    public EventTask(String taskDescription, LocalDateTime fromDateTime,
            LocalDateTime toDateTime, Tag taskTag) {
        super(taskDescription, taskTag);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    private EventTask(boolean isDone, String taskDescription,
            LocalDateTime from, LocalDateTime to, Tag taskTag) {
        super(isDone, taskDescription, taskTag);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsDone() {
        return super.isDone
                ? this
                : new EventTask(true, super.taskDescription, this.fromDateTime,
                        this.toDateTime, this.taskTag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task markAsUndone() {
        return super.isDone
                ? new EventTask(false, super.taskDescription, this.fromDateTime,
                        this.toDateTime, this.taskTag)
                : this;
    }

    /**
     * {@inheritDoc}
     * Additionally, {@code "[E]"} is prepended to the string representation to
     * indicate it
     * is a Event task. The date and time for the start and end times for the event
     * task is
     * appended to the string representation also.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + fromDateTime.format(CustomDateTimeFormatter.DATE_TIME_FORMATTER_PRETTY) + " to: "
                + toDateTime.format(CustomDateTimeFormatter.DATE_TIME_FORMATTER_PRETTY) + ")";
    }
}
