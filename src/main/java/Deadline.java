public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
