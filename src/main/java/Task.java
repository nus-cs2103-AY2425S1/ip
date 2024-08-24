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
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }
    public String unmarkDone() {
        this.isDone = false;
        return this.toString();
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
