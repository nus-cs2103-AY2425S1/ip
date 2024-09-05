package loafy.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents an event task with specified start and end date and time.
 */
public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs an undone event task with the specified name and start and end date and time.
     *
     * @param name The name of the event.
     * @param startDate The start date and time of the event of type {@code LocalDateTime}.
     * @param endDate The end date and time of the event of type {@code LocalDateTime}.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an event task with the specified status (done or undone), name and start and end date and time.
     *
     * @param isDone The state of the task ({@code true} for done and {@code false} for undone).
     * @param name The name of the event.
     * @param startDate The start date and time of the event of type {@code LocalDateTime}.
     * @param endDate The end date and time of the event of type {@code LocalDateTime}.
     */
    public Event(boolean isDone, String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(isDone, name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of this event task for user view.
     * The string representation is in the format "[E][status] name (from: start date to: end date)",
     * "[E]" symbolises type of task (event);
     * status is represented by "X" if task is done and " " if task is undone;
     * start and end dates are in "d/M/yyyy HHmm" format.
     *
     * @return a string representation of this event task.
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(), this.startDate.format(formatter), this.endDate.format(formatter));
    }

    /**
     * Returns a string representation of this event task for data storing.
     * The string representation is in the format "type,status,name,start date,end date", where:
     * type = "E";
     * status = "true" if task is done and "false" if task is not done;
     * name is exactly the name of the task;
     * start and end dates are in "d/M/yyyy HHmm" format.
     *
     * @return a string representation of this event task.
     */
    public String convertToTxt() {
        return String.format("%s,%s,%s,%s","E", super.convertToTxt(), this.startDate, this.endDate);
    }
}