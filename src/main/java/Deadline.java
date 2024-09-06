public class Deadline extends Task {
    private final String by;

    Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String toFile() { return "D/" + super.toFile() + "/" + by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
