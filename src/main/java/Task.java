// solution is adapted from the Course Level 3 extension

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        return type;
    }

    public abstract String getDescription();

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}
