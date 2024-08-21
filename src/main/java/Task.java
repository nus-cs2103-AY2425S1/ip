public class Task {
    protected String name = "";
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.name);
    }
}
