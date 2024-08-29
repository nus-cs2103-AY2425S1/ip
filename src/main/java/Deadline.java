public class Deadline extends Task {
    protected String by;

    @Override
    public String serialize() {
        return String.format("D|%b|%s|%s", this.isDone, this.description, this.by);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
