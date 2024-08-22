// Task with a deadline
public class DeadlineTask extends Task {
    String deadline;
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String taskType() {
        return "Event Task";
    }
    @Override
    public String toString() {
        String formattedDeadline = String.format("(by: %s)", deadline);
        return "[D]" + super.toString() + " " + formattedDeadline;
    }
}
