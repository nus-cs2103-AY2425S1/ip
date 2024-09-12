package tasks;

public abstract class Task {
    protected String description;
    protected String note = "No notes! :)";
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String note) {
        this.description = description;
        this.note = note;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getTimings();

    public abstract String getSymbol();

    public String getNote() {
        return this.note;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
