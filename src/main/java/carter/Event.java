package carter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a task with event in Carter
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with specified description, start time and end time.
     *
     * @param description The description of the task.
     * @param from The date and time by which the task should be started.
     * @param to The date and time by which the task should be completed.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);

        assert from != null : "start time should not be null";
        assert to != null : "end time should not be null";

        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with specified description, start time, end time and completion status.
     *
     * @param description The description of the task.
     * @param from The date and time by which the task should be started.
     * @param to The date and time by which the task should be completed.
     * @param isDone The completion status of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);

        assert from != null : "start time should not be null";
        assert to != null : "end time should not be null";

        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the task, including its type, description,
     * and deadline.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + "|"
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "|"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
