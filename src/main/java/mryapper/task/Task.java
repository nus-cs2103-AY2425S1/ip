package mryapper.task;

public abstract class Task {
    private final String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public int getStatus() {
        return isDone ? 1 : 0;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    // gets the string data to be written in the data file
    public abstract String getDataString();
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
