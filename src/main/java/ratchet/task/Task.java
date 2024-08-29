package ratchet.task;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done ratchet.task with X
    }

    @Override
    public String toString() {
        String check = "[" + getStatusIcon() + "]";
        return check + " " + description;
    }

    public String toSave() {
        return description + "|" + isDone;
    }
}
