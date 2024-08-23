public class DeadlineTask extends Task {
    private String deadline;
    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[D][X] " + this.getTaskName() + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.getTaskName() + " (by: " + this.deadline + ")";
        }
    }
}
