package trackbot.task;

import trackbot.ui.Parser;

/**
 * Deadline task with deadline.
 */
public class Deadline extends Task {
    private String dateby;

    /**
     * Constructs a Deadline task with the specified description and deadline date and/or time.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date and/or time of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dateby = by;
        this.dateby = Parser.checkDateFormat(by);
    }

    public String getBy() {
        return dateby;
    }
    @Override
    public String toStorageFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + desc + " | " + dateby;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateby + ")";
    }
}
