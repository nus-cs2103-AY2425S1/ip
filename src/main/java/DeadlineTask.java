public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + getDetails();
    }

    @Override
    public String getDetails() {
        return " (by: " + by + ")";
    }
}
