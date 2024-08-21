public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
