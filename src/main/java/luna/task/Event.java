package luna.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with start and end time.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates an Event.
     *
     * @param description Description of the event.
     * @param startTime Starting time of the event.
     * @param endTime Ending time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a, E dd MMM yyyy");
        return "[E]" + super.toString() + " (Start: " + startTime.format(formatter)
                + " ~ End: " + endTime.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        String status = super.isDone ? "1|" : "0|";
        return "E|" + status + super.description + "|" + startTime + "|" + endTime;
    }
}
