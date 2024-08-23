public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String toString() {
        return description;
    }
}
