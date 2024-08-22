public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String changeFormat() {
        return "D | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.by;
    }
}