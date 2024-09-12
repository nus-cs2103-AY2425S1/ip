package orion.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end time.
 *
 * <p>
 * An Event task has a description and a time range during which the event
 * occurs. It extends the base Task class and provides additional
 * functionality specific to events.
 * </p>
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event task.
     *
     * @param taskId      the unique identifier for the task.
     * @param description the description of the task.
     * @param from        the start date and time of the event.
     * @param to          the end date and time of the event.
     */
    public Event(int taskId, String description, LocalDateTime from, LocalDateTime to) {
        super(taskId, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start date and time of the event.
     *
     * @return the start date and time.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end date and time of the event.
     *
     * @return the end date and time.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the type icon for this task.
     *
     * @return a string representing the task type icon.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a string representation of this task.
     *
     * <p>
     * The string representation includes the task's description and the
     * formatted start and end times.
     * </p>
     *
     * @return a string representing the task, including the description and
     *         formatted start and end times.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
