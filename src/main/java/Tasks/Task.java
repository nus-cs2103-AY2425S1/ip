package tasks;

public abstract class Task {
    protected String name;
    protected Boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    // Does not consider previous state handling
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    public abstract String getWriteTaskInfo();

    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskStatus(), this.name);
    }
}
