public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String mark() {
        this.isDone = !this.isDone;
        return getString();
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getStorageFormat() {
        return "";
    }
}
