package task;

public class Task {
    private String description;

    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return (isDone() ? "[X] " : "[] ") + description;
    }
}
