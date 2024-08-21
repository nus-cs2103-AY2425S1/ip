public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return this.isDone;
    }
    public String getDescription() {
        return this.description;
    }

    public String stringStatus() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void changeStatus() {
        this.isDone = !isDone;
    }
    @Override
    public String toString() {
        return "[" + stringStatus() + "] " + this.description;
    }
}
