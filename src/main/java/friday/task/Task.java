package friday.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
