package mysutong;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    // Override this method in subclasses to properly save to file
    public String toFileString() {
        return String.format("%s | %d | %s", this.getClass().getSimpleName().charAt(0), (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
