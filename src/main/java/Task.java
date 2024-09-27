public class Task {
    protected boolean isDone;
    protected String taskName;

    public Task(String name) {
        this.taskName = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public String saveFormat() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
}
