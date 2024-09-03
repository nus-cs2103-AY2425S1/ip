package task;

import java.time.LocalDateTime;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from The start time of the event in yyyy-MM-dd HHmm format.
     * @param to The end time of the event in yyyy-MM-dd HHmm format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Constructs an Event with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from The start time of the event in yyyy-MM-dd HHmm format.
     * @param to The end time of the event in yyyy-MM-dd HHmm format.
     * @param isDone Whether the event is done or not.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the event in data format.
     *
     * @return The data format string representation of the event.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.format(INPUT_FORMAT) + " - " + to.format(INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        int len = super.toString().length();
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + "\n"
                + ui.Ui.INDENT.repeat(3) + " ".repeat(len - 1)
                + "to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
