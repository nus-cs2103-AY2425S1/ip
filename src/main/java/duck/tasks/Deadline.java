package duck.tasks;

/**
 * Class representing a "deadline" task.
 */
public class Deadline extends Task {
    private final DateAndTime deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description Task description.
     * @param deadline    Due date.
     */
    public Deadline(String description, DateAndTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getSaveString() {
        return String.format("D,%s,%s,%s", isDone, description, deadline.getOriginalString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", deadline.toString());
    }
}
