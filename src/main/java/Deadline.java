public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, "deadline", isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D|" + isDone + "|" + description + "|" + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
