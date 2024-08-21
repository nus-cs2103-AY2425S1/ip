/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", endTime);
    }
}
