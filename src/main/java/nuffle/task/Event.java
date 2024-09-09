package nuffle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time frame.
 * In addition to the task description, an event task has a start time ('from')
 * and an end time ('to').
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Construct an Event object.
     * @param description Describes what the Event object is.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        // Add a [D] at the front of task description (parent class)
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    /**
     * Converts the event task to a specific string format for saving to a file.
     * The format includes whether the task is done, its description,
     * and the event's start and end times in the "yyyy-MMM-dd HHmm" format.
     * @return A string representation of the event task suitable for saving to a file.
     */
    public String printSaveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "E | " + temp + " | " + description + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"))
                + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
    }

}
