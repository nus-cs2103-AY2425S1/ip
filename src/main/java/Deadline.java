public class Deadline extends Task{
    private final String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isComplete, String by) {
        super(description, isComplete);
        this.by = by;
    }

    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
