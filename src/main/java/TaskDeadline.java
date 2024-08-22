public class TaskDeadline extends Task {
    public TaskDeadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
