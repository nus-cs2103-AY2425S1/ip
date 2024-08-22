public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markTask() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}
