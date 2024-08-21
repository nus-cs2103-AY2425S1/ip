public class Task {
    protected String description;
    protected boolean isDone;
    private static int count = 0;
    private int taskNumber;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskNumber = ++count;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
