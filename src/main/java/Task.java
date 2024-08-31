public class Task {
    protected final String taskName;
    protected boolean isDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", taskName);
    }

    public String commaString() {
        return String.format("%s,%s", this.isDone ? "X" : " ", taskName);
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }
}
