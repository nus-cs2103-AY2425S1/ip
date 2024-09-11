package TaskType;

import java.util.Objects;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public boolean isEqual(Task task2) {
        return Objects.equals(this.toString(), task2.toString());
    }
}
