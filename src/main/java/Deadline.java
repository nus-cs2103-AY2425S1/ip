public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(boolean done, String name, String by) {
        super(done, name);
        this.by = by;
    }

    @Override
    public String toText() {
        return "D | " + super.toText() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", by);
    }
}
