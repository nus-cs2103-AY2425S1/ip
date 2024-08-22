public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by; // store the deadline
    }

    @Override
    public String toFileFormat() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
