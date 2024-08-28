public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public Deadline(String taskName, boolean isDone, String by) {
        super(taskName);
        this.by = by;
        if (isDone) {
            super.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
