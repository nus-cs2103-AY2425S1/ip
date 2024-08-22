public class Todo extends Task {
    public Todo(int taskNumber, String taskName, boolean taskCompletionStatus) {
        super(taskNumber, taskName, taskCompletionStatus);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
