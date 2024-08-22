public class Deadline extends Task {
    private String end;

    public Deadline(String name, String by) {
        super(name);
        this.end = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.end + ")";
    }
}
