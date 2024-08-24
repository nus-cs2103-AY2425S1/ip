public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskDescription;
    }
}
