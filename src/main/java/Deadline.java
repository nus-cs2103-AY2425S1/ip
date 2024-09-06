public class Deadline extends Task {
    private final String by;

    Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
