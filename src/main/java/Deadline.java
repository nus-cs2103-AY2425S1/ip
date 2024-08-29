public class Deadline extends Task {
    protected String by;
    public Deadline(String task, String by, boolean isCompleted) {
        super(task, isCompleted);
        this.by = by;
    }
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by;
    }
    public String toString() {

        return "[D]" + super.toString() + "(" + by + ")";
    }
}
