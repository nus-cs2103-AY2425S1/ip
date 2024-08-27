/**
 * tasks that need to be done before a specific date/time
 */
public class Deadline extends Task {
    private String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataFormat() {
        return "D" + super.toDataFormat() + "|" + by;
    }
}
