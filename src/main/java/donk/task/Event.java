package donk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime startDt;
    protected LocalDateTime endDt;

    /**
     * Constructs an donk.task.Event object with the specified description, start date-time, and end date-time.
     *
     * @param description The description of the event.
     * @param startDt The start date-time of the event in the specified format.
     * @param endDt The end date-time of the event in the specified format.
     */
    public Event(String description, String startDt, String endDt) {
        super(description, "E");
        try {
            this.startDt = LocalDateTime.parse(startDt);
            this.endDt = LocalDateTime.parse(endDt);
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            this.startDt = LocalDateTime.parse(startDt, formatter);
            this.endDt = LocalDateTime.parse(endDt, formatter);
        }

    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDt.toString() + " to " + this.endDt.toString() + ")";
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.startDt + "|" + this.endDt;
    }

}
