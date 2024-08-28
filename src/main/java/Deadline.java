public class Deadline extends Task {
    public String dueDate;
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " / by: " + dueDate ;
    }
}
