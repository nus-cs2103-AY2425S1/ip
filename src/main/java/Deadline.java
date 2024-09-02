public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getStatusIcon() {
        return super.getStatusIcon();
    }
    @Override
    public String toFormatted() {
        return "D," + this.isDone() + "," + this.description + "," + this.by + "\n";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
