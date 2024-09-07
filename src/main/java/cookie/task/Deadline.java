package cookie.task;

import java.time.LocalDate;

import cookie.parser.DateParser;

public class Deadline extends Task {
    private String by;
    private LocalDate deadline;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline date.
     *
     * @param description the description of the task
     * @param by the deadline date of the task, formatted as a string
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = DateParser.convertStringToDate(this.by);
    }

    /**
     * Constructs a new {@code Deadline} task with the specified completion status, description, and deadline date.
     *
     * @param isDone a boolean indicating whether the task is completed
     * @param description the description of the task
     * @param by the deadline date of the task, formatted as a string
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
        deadline = DateParser.convertStringToDate(this.by);
    }
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + (deadline != null ? DateParser.changePattern(deadline) : this.by) + ")";
    }
}
