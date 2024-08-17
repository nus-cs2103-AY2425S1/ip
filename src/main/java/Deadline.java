public class Deadline extends Task {
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = "by: " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.by + ")";
    }
}
