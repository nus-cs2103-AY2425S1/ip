public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSimplifiedString() {
        String formattedString = String.format("D | %d | %s | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription(), this.by);
        return formattedString;
    }
}