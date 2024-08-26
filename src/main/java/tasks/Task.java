package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toDataString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void setDone(boolean done) { this.isDone = done; }

    public boolean getDoneStatus() { return this.isDone; }

    public String getDes() { return this.description; }
}

