public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String parseTaskInfo() {
        return "D, "
                + (this.isCompleted ? "1, " : "0, ")
                + this.name + ", "
                + this.by
                + "\n";
    }
}
