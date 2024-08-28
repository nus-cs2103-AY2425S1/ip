package evan.task;

public class Deadline extends Task {
    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String encodeAsString() {
        return String.format("D | %s | %s | %s", (this.isDone ? "1" : "0"), description, by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}