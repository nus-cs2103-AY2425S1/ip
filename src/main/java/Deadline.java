public class Deadline extends Task {
    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(by: " + due + ")";
    }
}
