package task;

public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
    }
}
