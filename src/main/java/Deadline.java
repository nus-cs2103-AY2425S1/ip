public class Deadline extends Task {
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(getDescription(), this.by, true);
    }

    @Override
    public Deadline markAsNotDone() {
        return new Deadline(getDescription(), this.by, false);
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (by: " + by + ")";
    }
}