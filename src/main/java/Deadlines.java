public class Deadlines extends Task{

    String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline.replaceFirst("by ", "");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
