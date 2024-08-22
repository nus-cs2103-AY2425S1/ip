public class TaskDeadline extends Task {
    private final String deadline;

    public TaskDeadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", deadline.toString());
    }
}
