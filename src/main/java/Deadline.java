public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String prev = super.toString();
        return "[D" + prev.substring(2) + " (by: " + by + ")";
    }
}
