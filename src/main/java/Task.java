public class Task {
    private final String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    private String getMark() {
        return (this.isDone) ? "X" : " ";
    }

    public String toString() {
        return "[" + this.getMark() + "] " + this.task;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }
}