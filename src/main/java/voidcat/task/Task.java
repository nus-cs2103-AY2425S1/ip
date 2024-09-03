package voidcat.task;

public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // if task is done, mark with X
    }

    public abstract String getTaskType();

    public String toSaveFormat() {
        return String.format("%s | %d | %s", this.getTaskType(), (isDone ? 1 : 0), description);
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getStatusIcon() + " " + this.description;
    }

}
