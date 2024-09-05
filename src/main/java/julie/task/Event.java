package julie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents a task with a start and end date.
 */
public class Event extends Task {
    /** The start date of the task */
    private LocalDate start;
    /** The end date of the task */
    private LocalDate end;

    /**
     * Public constructors for the event class.
     *
     * @param taskDescription The string description for the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String taskDescription, LocalDate start, LocalDate end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
    @Override
    public String toStorageString() {
        return String.format("E | %s | %s | %s",this.taskString, this.start, this.end);
    }
}
