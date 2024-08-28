import java.time.LocalDateTime;
public class Deadline extends Task {
    private String deadline;
    public Deadline (int taskNumber, String taskName, boolean taskCompletionStatus, String deadline) {
        super(taskNumber, taskName, taskCompletionStatus);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String storageString() {
        return "[D]" + super.toString() + " /by " + this.deadline;
    }
}
