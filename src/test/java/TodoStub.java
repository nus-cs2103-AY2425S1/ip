import task.Task;
public class TodoStub extends Task {
    public String description;
    public boolean isDone;

    public TodoStub(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

}
