public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toFileFormat() {
        String task = "D";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description;
    }
}
