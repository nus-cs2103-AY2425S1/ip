/**
 * Represent a Deadline task.
 * This class extends the Task class, adding a deadline attribute to it.
 */
public class Deadline extends Task {
    /** The deadline in which the task should be completed by. */
    protected String by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline in which the task should be completed by.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
