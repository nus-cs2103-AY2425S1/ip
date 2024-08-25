public class Task {
    protected String description;
    protected boolean isDone;
    public static int count = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ((isDone ? "[X]" : "[ ]") + " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public static String taskCount() {
        return Task.count == 1
                ? "task"
                : "tasks";
    }

    public static String taskCountDelete() {
        return Task.count == 2
                ? "task"
                : "tasks";
    }
}
