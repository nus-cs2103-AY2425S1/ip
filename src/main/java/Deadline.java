/**
 * Represents a deadline to be recorded. A <code>Deadline</code> object
 * is represented by two Strings
 * e.g., <code>return book, Sunday</code>
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns summary of task
     *
     * @return task summary
     */
    public String summary() {
        return "D " + super.summary() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
