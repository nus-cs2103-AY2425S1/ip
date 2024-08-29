public class Deadline extends Task {
    protected String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
