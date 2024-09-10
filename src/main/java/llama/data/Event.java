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
     * Constructor for Event
     *
     * @param desc description for task to be done
     * @param startTime start time for task
     * @param endTime end time for task
     * @param isDone true if task is done; else false
     */
    public Event(String desc, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(desc, isDone);
        assert startTime != null && endTime != null;
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
