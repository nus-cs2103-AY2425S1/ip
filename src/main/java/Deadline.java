public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String formatString() {
        return String.format("D | %s | %s", super.formatString(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}
