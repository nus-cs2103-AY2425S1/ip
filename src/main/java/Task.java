public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.symbol = "[T]";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark(Task task) {
        task.isDone = true;
    }

    @Override
    public String toString() {
        return this.symbol + " [" + this.getStatusIcon() + "] " + this.description;
    }
}
