package yap.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate startTime;
    LocalDate endTime;

    /**
     * Constructs a task which is an event, with the event's description, startTime and endTime.
     *
     * @param description a description of the event.
     * @param startTime the starting time for the event.
     * @param endTime the ending time for the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    /**
     * Constructs a task which is an event, with the event's description, startTime and endTime.
     * The task can either be done or not done.
     *
     * @param description a description of the event.
     * @param startTime the starting time for the event.
     * @param endTime the ending time for the event.
     * @param isDone whether the task has been completed or not.
     */
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, E | Completion Status (0 or 1) | Description | Start Time | End Time
     */
    @Override
    public String convertToFileString() {
        return "E | " + super.convertToFileString()
                + String.format(" | %s | %s\n", startTime, endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
