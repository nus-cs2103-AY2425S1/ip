public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
    @Override
    public String parseToFile() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.dueDate;
    }
}
