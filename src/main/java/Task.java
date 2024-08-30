public class Task {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean markAsDone() {
        return isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
