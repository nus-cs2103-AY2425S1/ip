package fishman.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event task.
 * This class extends the Task class, adding the from and to attributes to it.
 */
public class Event extends Task {
    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;

    /**
     * Constructs a new Event task.
     *
     * @param taskDescription The description of the task.
     * @param eventStart The start time of the event.
     * @param eventEnd The end time of the event.
     */
    public Event(String taskDescription, boolean isTaskDone, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(taskDescription, isTaskDone);
        assert taskDescription != null : "Description should not be null";
        assert eventStart != null : "Event 'From' date should not be null";
        assert eventEnd != null : "Event 'To' date should not be null";
        assert eventStart.isBefore(eventEnd) : "Event 'From' date should be before 'To' date";
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        return super.toString() + " (from: " + eventStart.format(formatter) + " to: "
                + eventEnd.format(formatter) + ")";
    }
}
