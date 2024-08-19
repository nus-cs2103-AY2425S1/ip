public class Task {
    protected String desc;
    protected boolean isDone;

    public Task (String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
