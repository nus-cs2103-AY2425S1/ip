public class DeadlineTask extends Task {
    private String taskName;
    private String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public DeadlineTask(String taskName, boolean isCompleted, String deadline) {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (super.isCompleted() ? "1" : "0") + " | "
                + this.taskName + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
