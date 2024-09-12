package makima.task;

import java.time.LocalDateTime;

/**
 * Task with a specified start and end date
 */
public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Instantiates a new event
     *
     * @param name
     * @param startTime
     * @param endTime
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new event from a saved file
     *
     * @param name
     * @param startTime
     * @param endTime
     * @param isDone
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(name, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s, to: %s)", startTime, endTime);
    }

    @Override
    public String toFileString() {
        return String.format("E\n%s%s\n%s\n", super.toFileString(), startTime, endTime);
    }
}
