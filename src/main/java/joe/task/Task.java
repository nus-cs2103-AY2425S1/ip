package joe.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String serialize();
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public Task setIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public boolean contains(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
