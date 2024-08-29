package tissue;

public class Task {
    private final String task;
    private boolean isDone;

    public Task(boolean isDone, String task) {
        this.isDone = isDone;
        this.task = task;
    }

    public Task markTask() {
        isDone = true;
        return this;
    }

    public Task unmarkTask() {
        isDone = false;
        return this;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + task : "[ ] " + task;
    }
}
