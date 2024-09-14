package task;

/**
 * The Deadline class represents a task with a deadline
 */
public class Deadline extends Task {
    public static final String TASK_TYPE = "D";
    private String by;

    /**
     * Constructor for a Deadline
     * @param description String representing the task desrcription
     * @param by String representing the deadline
     */
    public Deadline(String description, String by) {
        super(description, Deadline.TASK_TYPE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
