public class Deadline extends Task {
    protected boolean isDone;
    protected String by;
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by =by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.by;
    }
}