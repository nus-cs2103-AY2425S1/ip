public class Task {
    private String taskName;
    private boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", taskName);
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }
}
