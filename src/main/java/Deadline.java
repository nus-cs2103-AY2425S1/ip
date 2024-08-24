public class Deadline extends Task {

    private String by;

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return "DEADLINE," + super.fileFormat() + "," + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
