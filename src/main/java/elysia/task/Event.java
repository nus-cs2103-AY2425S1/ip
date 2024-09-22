package elysia.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a description, starting time and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an event task.
     *
     * @param description The description of the task.
     * @param startTime   The start time of the event.
     * @param endTime     the end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns start time of the event.
     *
     * @return
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns end time of the event.
     * @return
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns a string representation of the Event task, including its description, start time, and end time.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("d MMM hh:mma"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a text file.
     *
     * @return
     */
    @Override
    public String saveToTxt() {
        int i = this.isDone ? 1 : 0;
        return "E | " + i + " | " + this.description + " | "
                + this.startTime + " | " + this.endTime;
    }
}
