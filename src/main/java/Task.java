public class Task {
    protected String description;

    protected boolean isDone;

    protected TaskType type;

    public Task(String description) {
        this.description = description;
        isDone = false;
        type = TaskType.TASK;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public enum TaskType {
        TASK,
        TODO,
        DEADLINE,
        EVENT
    }
}
