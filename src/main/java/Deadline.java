public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toFileString() {
        return String.format("D | %s | %s | %s", super.getStatus() ? "1" : "0", super.getDescription(), this.by);
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
