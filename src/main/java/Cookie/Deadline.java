package Cookie;

import java.time.LocalDate;

public class Deadline extends Task {
    private String by;
    private LocalDate deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of task.
     * @param by Deadline date of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = DateParser.convertStringToDate(this.by);
    }

    /**
     * Another constructor for Deadline class.
     *
     * @param isDone Whether the task is done or not.
     * @param description Description of task.
     * @param by Deadline date of task.
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
