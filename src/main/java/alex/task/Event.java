package alex.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a start and end date and time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event Task with the specified details.
     *
     * @param taskName The name or description of the task.
     * @param isCompleted A boolean indicating whether the task is completed.
     * @param startTime The start date and time of the event as a LocalDateTime object.
     * @param endTime The end date and time of the event as a LocalDateTime object.
     */
    public Event(String taskName, boolean isCompleted, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName, isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the String representation of the Task to be displayed to the user
     * that includes its completion status, description, start date, and end date.
     *
     * @return A String representation of the Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + " to: "
                + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    /**
     * Returns a String representation of the Event Task suitable for storage.
     * The format includes the task details, start date, and end date in "yyyy-MM-dd HHmm" format.
     *
     * @return A String representation of the Event Task for storage.
     */
    @Override
    public String toStorageString() {
        return "[E]" + super.toString() + " /from "
                + this.startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " /to " + this.endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

