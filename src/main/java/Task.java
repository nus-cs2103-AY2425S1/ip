public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatus() {
        return isDone ? "X" : "";
    }
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }
    public String unmarkDone() {
        this.isDone = false;
        return this.toString();
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description + "\n";
    }
}
