package TrackBot.task;

import TrackBot.ui.Parser;

/**
 * Deadline task with deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the specified description and deadline date and/or time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and/or time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.by = Parser.checkDateFormat(by);
    }

    public String getBy() {
        return by;
    }
    @Override
    public String toStorageFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
