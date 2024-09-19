package bro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task for saving/loading purposes.
     * The string includes the task type, status, description, start time, and end time in a specific format.
     *
     * @return A string representing the Event task, formatted for storage.
     *         Format: [E][status] description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
     */
    @Override
    public String toLoad() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "[E]" + super.toString() + " /from " +
                from.format(format) + " /to " + to.format(format);
    }

    /**
     * Returns a string representation of the Event task for display purposes.
     * The string includes the task type, status, description, start time, and end time in a human-readable format.
     *
     * @return A string representing the Event task, formatted for display.
     *         Format: [E][status] description (from: dd LLL yyyy HHmm to: dd LLL yyyy HHmm)
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd LLL yyyy HHmm");
        return "[E]" + super.toString() + " (from: " +
                from.format(format) + " to: " + to.format(format) + ")";
    }
}

