package Cookie;
public abstract class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }
    public String getStatus() {
        return isDone ? "X" : "";
    }
    public void markDone() {
        this.isDone = true;
    }
    public void unmarkDone() {
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
