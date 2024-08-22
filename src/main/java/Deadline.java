public class Deadline extends Task {
    private String deadline;
    public Deadline (int taskNumber, String taskName, boolean taskCompletionStatus, String deadline) {
        super(taskNumber, taskName, taskCompletionStatus);
        this.deadline = deadline;
    }
}
