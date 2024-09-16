package jade.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline with the specified description, deadline date, and completion status.
     *
     * @param description The description of the deadline.
     * @param by The deadline date in yyyy-MM-dd format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Constructs a Deadline with the specified description, deadline date, and completion status.
     *
     * @param description The description of the deadline.
     * @param by The deadline date in yyyy-MM-dd format.
     * @param isDone Whether the deadline is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description
                + " | " + by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
