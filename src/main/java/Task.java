public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public abstract String toStorageFormat();

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
