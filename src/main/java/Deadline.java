public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, String by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | " + this.by;
    }
}
