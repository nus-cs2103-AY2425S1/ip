public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalTasks += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void changeStatus(boolean b) {
        this.isDone = b;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static int getTotalTasks() {
        return totalTasks;
    }
}