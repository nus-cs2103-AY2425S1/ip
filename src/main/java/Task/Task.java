package Task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public abstract String formatForStorage();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}