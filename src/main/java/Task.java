public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    public boolean markAsNotDone() {
        this.isDone = false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public abstract String toSave();
}
