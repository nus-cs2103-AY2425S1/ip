public class Task {
    private String taskName;

    private int taskNumber;

    private boolean taskCompletionStatus;
    public Task(int taskNumber, String taskName, boolean taskCompletionStatus) {
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.taskCompletionStatus = taskCompletionStatus;
    }

    public void markAsDone() {
        this.taskCompletionStatus = true;
    }

    public void markAsUndone() {
        this.taskCompletionStatus = false;
    }
    @Override
    public String toString() {
        String box = "";
        if (this.taskCompletionStatus) {
            box = "[X]";
        } else {
            box = "[ ]";
        }

        return String.format("%s %s", box, this.taskName);
    }
}
