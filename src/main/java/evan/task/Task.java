package evan.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String encodeAsString();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean containsMatchingDescription(String description) {
        if (this.description.contains(description)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
