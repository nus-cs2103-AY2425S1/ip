public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusLabel() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusLabel(), this.description);
    }
}
