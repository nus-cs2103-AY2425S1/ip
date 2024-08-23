public class Deadline extends Task {
    private String duedate;
    public Deadline(String name, String duedate) {
        super(name);
        this.duedate = duedate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.duedate + ")";
    }
}
