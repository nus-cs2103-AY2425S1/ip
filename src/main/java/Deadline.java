/**
 * This class encapsulates a Deadline type task.
 * A Deadline needs to be done before a specific time.
 */
public class Deadline extends Task {
    /** The deadline of the task */
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
