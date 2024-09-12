package henry.task;

/**
 * Represents a deadline to be recorded. A <code>Deadline</code> object
 * is represented by two Strings.
 * e.g., <code>return book, 2019-12-01 1900</code>
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     * @param by Date to finish the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets up the instance.
     *
     * @param description Description of the task.
     * @param by Date to finish the task.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns summary of task.
     *
     * @return Task summary.
     */
    @Override
    public String summary() {
        return "D " + super.summary() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
