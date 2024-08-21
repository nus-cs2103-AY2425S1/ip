public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString() + " (by: " + by + ")";
    }
}
