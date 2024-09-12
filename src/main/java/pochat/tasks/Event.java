package pochat.tasks;

import java.time.LocalDateTime;

/**
 * This object represents a Task that has a task description as well
 *     as a duration over which the task would happen or is carried out
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Takes in the necessary parameters to construct the Event object
     * @param taskDescription the descrption of the task
     * @param startTime when the event starts in dd/mm/yyyy HHMM format
     * @param endTime when the event ends in dd/mm/yyyy HHMM format
     * @param isDone representing if the event is done
     */
    public Event(String taskDescription, String startTime, String endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.startTime = toLocalDateTime(startTime);
        this.endTime = toLocalDateTime(endTime);
    }

    public Event(String taskDescription, String startTime, String endTime) {
        this(taskDescription, startTime, endTime, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatYearMonthDay(this.startTime)
                + " " + "to: " + formatYearMonthDay(this.endTime) + ")";
    }
}
