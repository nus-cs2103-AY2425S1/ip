public class Deadline extends Task{
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date, boolean isDone) {
        super(description);
        this.date = date;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]" + " " + this.description + " (by: " + this.date + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }
}
