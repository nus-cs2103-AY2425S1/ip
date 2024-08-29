public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean status, String description, String deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadline;
    }

    public String toStorage() {
        return "D," + super.toStorage() + "," + deadline;
    }
}
