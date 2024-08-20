public class Task {
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void unmarkAsDone() {
        isDone = false;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", task);
    }
}
