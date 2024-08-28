package shrimp.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by, boolean isDone) {
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

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (by: " + by + ")";
    }
}