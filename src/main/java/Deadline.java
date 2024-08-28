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
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("DEADLINE | %s | %s | %s\n", status, this.description, this.by);
    }
}
