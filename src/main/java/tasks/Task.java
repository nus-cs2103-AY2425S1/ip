package tasks;

public class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s", status, this.description);
    }

    public String toFileString() {
        String status = this.isDone ? "1" : "0";
        return String.format("%s | %s", status, this.description);
    }
}
