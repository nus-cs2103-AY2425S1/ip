package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * task.Event class that extends the task.Task class, with startTime and endTime that are defined
 */
public class Event extends Task {
    public String startTime;
    public String endTime;
    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    public final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Checks if the startTime and endTime can be parsed as LocalDateTime objects
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        try {
            this.startDateTime = LocalDateTime.parse(startTime, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.startDateTime = null;
        }
        try {
            this.endDateTime = LocalDateTime.parse(endTime, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            this.endDateTime = null;
        }
    }
    @Override
    public String toString() {
        String beginning = (this.startDateTime != null) ? this.startDateTime.format(OUTPUT_FORMAT) : this.startTime;
        String end = (this.endDateTime != null) ? this.endDateTime.format(OUTPUT_FORMAT) : this.endTime;
        return taskIsDone ? "[E][X] " + this.description + " (from: " + beginning + " to: " + end + ")" : "[E][ ] " + this.description + " (from: " + beginning + " to: " + end + ")";
    }
}
