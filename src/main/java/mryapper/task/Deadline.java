package mryapper.task;

/**
 * A type of task which has a deadline.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Creates a task with a deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getDataString() {
        return String.format("D ||| %d ||| %s ||| %s\n",
                this.getStatus(), this.getDescription(), this.deadline);
    }

    @Override
    public String toString() {
        String deadlineString = String.format(" (by: %s)", deadline);
        return "[D]" + super.toString() + deadlineString;
    }
}
