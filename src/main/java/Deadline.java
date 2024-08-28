public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.by = by;
        if (isDone) markAsDone();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}