package llama.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a start and end time
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates Event object
     *
     * @param desc description of event to attend
     * @param startTime start time of event
     * @param endTime end time of event
     * @param isDone true if event is done; else false
     */
    public Event(String desc, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(desc, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "E" + super.getSaveTaskString() + "|"
                + this.startTime.format(FORMATTER) + "|" + this.endTime.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + this.startTime.format(FORMATTER) + "|to: " + this.endTime.format(FORMATTER) + ")";
    }
}
