import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final long serialVersionID = 1L;

    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return isDone ? "[X] " + description : "[ ] " + description;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getInitDesc();
}
