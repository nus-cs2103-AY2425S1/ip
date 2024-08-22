public class Task {
    protected final String name;
    protected boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    protected boolean isCompleted() {
        return this.completed;
    }

    protected void markComplete() {
        this.completed = true;
    }

    protected void markUncomplete() {
        this.completed = false;
    }

    protected String getStatusIcon() {
        return (this.isCompleted() ? "X" : " ");
    }

    @Override
    public String toString() {
        return "["
                + this.getStatusIcon()
                + "] "
                + this.name;
    }
}
