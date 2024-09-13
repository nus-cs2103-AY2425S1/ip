package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an event task with a start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event, represented as a LocalDateTime object.
     * @param end The end time of the event, represented as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param start The start time of the event, represented as a LocalDateTime object.
     * @param end The end time of the event, represented as a LocalDateTime object.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event suitable for saving to storage.
     * The format includes a task type identifier, description, completion status, and the start and end time.
     *
     * @return A string representing the event in a saveable format.
     */
    @Override
    public String saveString() {
        DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        String formattedStart = (start != null) ? start.format(saveFormatter) : "N/A";
        String formattedEnd = (end != null) ? end.format(saveFormatter) : "N/A";
        return "E" + super.saveString() + " | " + formattedStart + " | " + formattedEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(start, event.start)
                && Objects.equals(end, event.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), start, end);
    }

    /**
     * Returns a string representation of the Event task for display.
     * Includes the task's description, start time, and end time formatted for readability.
     *
     * @return A string representing the event, including its start and end times.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedStart = (start != null) ? start.format(formatter)
                : "Fail to set start time, check time format: dd-MM-yyyy HHmm";
        String formattedEnd = (end != null) ? end.format(formatter)
                : "Fail to set end time, check time format: dd-MM-yyyy HHmm";
        return "[E]" + super.toString()
                + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
