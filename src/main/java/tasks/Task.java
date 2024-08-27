package tasks;

import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(this.description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description);
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

