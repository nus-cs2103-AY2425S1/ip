public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;

    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String printTask() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }

}
